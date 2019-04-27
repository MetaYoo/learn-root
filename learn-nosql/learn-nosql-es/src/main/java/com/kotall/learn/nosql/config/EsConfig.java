package com.kotall.learn.nosql.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/4/27 18:47
 */
@Configuration
public class EsConfig {

    @Bean
    public RestHighLevelClient client() {

        HttpHost httpHost = new HttpHost("127.0.0.1", 9200, "http");
        RestClientBuilder builder = RestClient.builder(httpHost);
        RestHighLevelClient client = new RestHighLevelClient(builder);

        return client;
    }
}
