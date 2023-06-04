package akira.d.chatbot.api.application.job;

import akira.d.chatbot.api.domain.ai.IOpenAI;
import akira.d.chatbot.api.domain.zsxq.IZsxqApi;
import akira.d.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import akira.d.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @author Zongjin Dai
 * @description schedule chatbot
 */
@EnableScheduling
@Configuration
public class ChatbotSchedule {

    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

    @Value("${chatgptbot.groupId}")
    private String groupId;
    @Value("${chatgptbot.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAI openAI;

    //cron.qqe2.com
    @Scheduled(cron = "0/30 * * * * ?")
    public void run(){
        try{
            if(new Random().nextBoolean()){
                logger.info("not working today");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            /*if(hour > 22 || hour < 6){
                logger.info("sleeping ZZZ");
                return;
            }*/

            //1. index question
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("result: {}", JSON.toJSONString(unAnsweredQuestionsAggregates));

            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if(null == topics || topics.isEmpty()){
                logger.info("no questions found during this session");
                return;
            }

            //2.ai answer
            Topics topic = topics.get(topics.size()-1);
            String answer = openAI.doChatGpt(topic.getQuestion().getText().trim());

            //3.answer question
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            logger.info("number: {} question: {} answer: {} status: {}", topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
        }catch(Exception e){
            logger.error("automatic answer failure", e);
        }
    }
}
