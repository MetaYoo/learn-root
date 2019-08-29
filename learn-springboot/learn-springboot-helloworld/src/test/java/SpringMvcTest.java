import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/8/29 17:58
 * @since 1.0.0
 */
public class SpringMvcTest {


    @Test
    public void springMvc() throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            //HttpMethod method = new GetMethod("http://localhost:8080/test?name=test-" + i + "&age=" + i);
            RequestConfig requestConfig = RequestConfig.custom().setProxy(new HttpHost("localhost", 8888)).build();
            HttpUriRequest httpUriRequest = RequestBuilder.get()
                    .setConfig(requestConfig)
                    //.setUri("http://localhost:8080/test")
                    .setUri("/test")
                    .addParameter("name", "test-" + i)
                    .addParameter("age", "" + i).build();


            executorService.submit(new HttpTask(httpClient, httpUriRequest));
        }

        Thread.sleep(60 * 1000);
    }

    static class HttpTask implements Runnable {
        HttpClient httpClient;
        HttpUriRequest httpRequest;

        public HttpTask(HttpClient httpClient, HttpUriRequest httpRequest) {
            this.httpClient = httpClient;
            this.httpRequest = httpRequest;
        }

        @Override
        public void run() {
            try {
                //HttpHost.create("http://localhost:8080/test");
                HttpHost host = HttpHost.create("http://localhost:8080");
                HttpResponse response = httpClient.execute(host, httpRequest);
                StatusLine status = response.getStatusLine();
                if (status.getStatusCode() == 200) {
                    String resp = EntityUtils.toString(response.getEntity());
                    System.out.println(resp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
