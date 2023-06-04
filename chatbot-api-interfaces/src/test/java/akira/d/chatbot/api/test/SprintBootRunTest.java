package akira.d.chatbot.api.test;

import akira.d.chatbot.api.domain.ai.IOpenAI;
import akira.d.chatbot.api.domain.zsxq.IZsxqApi;
import akira.d.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import akira.d.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SprintBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SprintBootRunTest.class);

    @Value("${chatgptbot.groupId}")
    private String groupId;
    @Value("${chatgptbot.cookie}")
    private String cookie;
    @Value("${chatgptbot.openAIkey}")
    private String openAIkey;

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAI openAI;

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("result: {}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for(Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();

            logger.info("topicId: {} text: {}", topicId, text);

            //answer question
            zsxqApi.answer(groupId, cookie, topicId, text, false);
        }
    }

    @Test
    public void test_openAI() throws IOException{
        String response = openAI.doChatGpt("write bubble sort in java");
        logger.info("result: {}", response);
    }
}
