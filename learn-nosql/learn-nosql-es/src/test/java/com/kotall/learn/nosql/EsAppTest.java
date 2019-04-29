package com.kotall.learn.nosql;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsAppTest {

    @Resource
    private RestHighLevelClient client;


    /**
     * 创建索引
     *
     * @throws Exception
     */
    @Test
    public void createIdx() throws Exception {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");
        XContentBuilder builder = XContentFactory.yamlBuilder();

        CreateIndexResponse createIndexResponse = this.client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.toString());
    }


    @Test
    public void search() throws Exception {
        GetRequest getRequest = new GetRequest();
        getRequest.index("test");
        getRequest.id("3");
        // GET /test/employee/3?_source=title,text
        getRequest.fetchSourceContext();

        GetResponse getResponse = this.client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSource());
    }
}
