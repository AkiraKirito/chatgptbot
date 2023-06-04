package akira.d.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * @author Zongjin Dai
 * @description open ai api : https://platform.openai.com/account/api-keys
 */
public interface IOpenAI {

    String doChatGpt(String question) throws IOException;

}
