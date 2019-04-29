package com.kotall.learn.nosql;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        Settings settings = Settings.builder()
                .build();
        createIndexRequest.settings(settings);
        CreateIndexResponse createIndexResponse = this.client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.toString());
    }

    /**
     * 删除索引
     * @throws Exception
     */
    @Test
    public void delIdx() throws Exception {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("user");
        AcknowledgedResponse response = this.client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        Assert.assertTrue(response.isAcknowledged());
    }


    /**
     * 索引文档
     * @throws Exception
     */
    @Test
    public void index() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        builder.field("id", "1");
        builder.field("name", "tester");
        builder.field("age", 20);
        builder.field("birthday", "1999-04-29");
        builder.field("createDate", new Date());
        builder.endObject();
        IndexRequest indexRequest = new IndexRequest("user");
        indexRequest.source(builder);
        this.client.index(indexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 删除文档
     * @throws Exception
     */
    @Test
    public void deleteDoc() throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest("user");
        deleteRequest.id("p4XoaGoBO4WELwKxlTww");
        DeleteResponse deleteResponse = this.client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status().getStatus());
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
