package com.kotall.learn.nosql;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/8 21:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsQueryTest {
    @Resource
    private RestHighLevelClient client;

    @Test
    public void testQuery1() throws Exception {
        SearchRequest request = new SearchRequest();

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();
        searchSourceBuilder.query(QueryBuilders.termQuery("name", "test1"));
        request.source(searchSourceBuilder);

        SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    public void testQuery2() throws Exception {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", "test2"));
        request.source(searchSourceBuilder);
        SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    public void testQuery3() throws Exception {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();
        searchSourceBuilder.query(QueryBuilders.boolQuery());

        request.source(searchSourceBuilder);
        SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }


}
