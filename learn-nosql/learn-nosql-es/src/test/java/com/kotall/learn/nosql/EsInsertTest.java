package com.kotall.learn.nosql;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/8 21:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsInsertTest {
    @Resource
    private RestHighLevelClient client;

    @Test
    public void testInsert1() throws Exception {
        IndexRequest indexRequest = new IndexRequest("user");
        indexRequest.id("4");
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        builder.field("id", "4");
        builder.field("name", "test3");
        builder.field("age", 27);
        builder.field("birthday", "1992-05-08");
        builder.field("createDate", new Date());
        builder.endObject();
        indexRequest.source(builder);
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    @Test
    public void testBulk() throws Exception {
        BulkRequest bulkRequest = new BulkRequest("user");
        XContentBuilder builder = XContentFactory.jsonBuilder();
        bulkRequest.add(new IndexRequest("user").id("5").source(XContentFactory.jsonBuilder().startObject()
                .field("id", "5")
                .field("name", "test5")
                .field("age", 26)
                .field("birthday", "1993-05-08")
                .field("createDate", new Date())
                .endObject()));
        bulkRequest.add(new IndexRequest("user").id("6").source(XContentFactory.jsonBuilder().startObject()
                .field("id", "6")
                .field("name", "test6")
                .field("age", 27)
                .field("birthday", "1992-05-08")
                .field("createDate", new Date())
                .endObject()));
        bulkRequest.add(new IndexRequest("user").id("7").source(XContentFactory.jsonBuilder().startObject()
                .field("id", "7")
                .field("name", "test7")
                .field("age", 28)
                .field("birthday", "1991-05-08")
                .field("createDate", new Date())
                .endObject()));
        BulkResponse bulkResponse = this.client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse);
    }

    @Test
    public void testArray() throws Exception {
        IndexRequest indexRequest = new IndexRequest("user");
        indexRequest.id("8");
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        builder.field("id", "8");
        builder.field("name", "test8");
        builder.field("age", 28);
        builder.startArray("interests")
                .value("sport")
                .value("running")
                .value("thinking")
                .value("writing")
                .endArray();
        builder.field("birthday", "1991-05-01");
        builder.field("createDate", new Date());
        builder.endObject();
        indexRequest.source(builder);
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }
}
