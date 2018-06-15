package com.kotall.learn.springcloud.eureka.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/11 0011 下午 4:49
 */
public class TestClient {

    @Test
    public void test() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        for (int i = 0; i < 10; i++) {
            HttpGet httpGet = new HttpGet("http://localhost:8961/router");

            HttpResponse response = httpClient.execute(httpGet);

            System.out.println(EntityUtils.toString(response.getEntity()));
        }
    }
}
