package akira.d.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885282184451/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", "sajssdk_2015_cross_new_user=1; zsxq_access_token=CEEE418A-6770-5191-8DDC-521DECF8CAEB_89FE4A83182C157F; zsxqsessionid=5b3b65d5937989e016fb4e28dedfcad8; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22415425824441818%22%2C%22first_id%22%3A%221887cf10cb78d-05609215c5b4d9c-5a4f2f16-2712060-1887cf10cb8b16%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg4N2NmMTBjYjc4ZC0wNTYwOTIxNWM1YjRkOWMtNWE0ZjJmMTYtMjcxMjA2MC0xODg3Y2YxMGNiOGIxNiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjQxNTQyNTgyNDQ0MTgxOCJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22415425824441818%22%7D%2C%22%24device_id%22%3A%221887cf10cb78d-05609215c5b4d9c-5a4f2f16-2712060-1887cf10cb8b16%22%7D");
        get.addHeader("Content-Type", "application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }
        else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/412285242285458/answer");
        post.addHeader("cookie", "sajssdk_2015_cross_new_user=1; zsxq_access_token=CEEE418A-6770-5191-8DDC-521DECF8CAEB_89FE4A83182C157F; zsxqsessionid=5b3b65d5937989e016fb4e28dedfcad8; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22415425824441818%22%2C%22first_id%22%3A%221887cf10cb78d-05609215c5b4d9c-5a4f2f16-2712060-1887cf10cb8b16%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg4N2NmMTBjYjc4ZC0wNTYwOTIxNWM1YjRkOWMtNWE0ZjJmMTYtMjcxMjA2MC0xODg3Y2YxMGNiOGIxNiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjQxNTQyNTgyNDQ0MTgxOCJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22415425824441818%22%7D%2C%22%24device_id%22%3A%221887cf10cb78d-05609215c5b4d9c-5a4f2f16-2712060-1887cf10cb8b16%22%7D");
        post.addHeader("Content-type", "application/json; charset=UTF-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"正在前往花村\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }
        else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_chatgpt() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");
        post.addHeader("Content-type", "application/json");
        post.addHeader("Authorization", "Bearer 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");

        String paramJson = "{\n" +
                "     \"model\": \"gpt-3.5-turbo\",\n" +
                "     \"messages\": [{\"role\": \"user\", \"content\": \"write a bubble sort in java\"}],\n" +
                "     \"temperature\": 0.7\n" +
                "   }";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }
        else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
