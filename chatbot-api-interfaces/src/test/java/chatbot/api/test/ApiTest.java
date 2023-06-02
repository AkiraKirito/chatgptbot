package chatbot.api.test;

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

        get.addHeader("cookie", "sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross={\"distinct_id\":\"1887cf10cb78d-05609215c5b4d9c-5a4f2f16-2712060-1887cf10cb8b16\",\"first_id\":\"\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg4N2NmMTBjYjc4ZC0wNTYwOTIxNWM1YjRkOWMtNWE0ZjJmMTYtMjcxMjA2MC0xODg3Y2YxMGNiOGIxNiJ9\",\"history_login_id\":{\"name\":\"\",\"value\":\"\"},\"$device_id\":\"1887cf10cb78d-05609215c5b4d9c-5a4f2f16-2712060-1887cf10cb8b16\"}; zsxq_access_token=CEEE418A-6770-5191-8DDC-521DECF8CAEB_89FE4A83182C157F; zsxqsessionid=5b3b65d5937989e016fb4e28dedfcad8; abtest_env=product");
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

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/814482454488442/answer");
        post.addHeader("cookie", "sajssdk_2015_cross_new_user=1; zsxq_access_token=CEEE418A-6770-5191-8DDC-521DECF8CAEB_89FE4A83182C157F; zsxqsessionid=5b3b65d5937989e016fb4e28dedfcad8; abtest_env=product; sensorsdata2015jssdkcross={\"distinct_id\":\"415425824441818\",\"first_id\":\"1887cf10cb78d-05609215c5b4d9c-5a4f2f16-2712060-1887cf10cb8b16\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg4N2NmMTBjYjc4ZC0wNTYwOTIxNWM1YjRkOWMtNWE0ZjJmMTYtMjcxMjA2MC0xODg3Y2YxMGNiOGIxNiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjQxNTQyNTgyNDQ0MTgxOCJ9\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"415425824441818\"},\"$device_id\":\"1887cf10cb78d-05609215c5b4d9c-5a4f2f16-2712060-1887cf10cb8b16\"}");
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
}
