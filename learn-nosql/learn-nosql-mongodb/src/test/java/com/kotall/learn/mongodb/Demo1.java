package com.kotall.learn.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/10/12 16:12
 * @since 1.0.0
 */
public class Demo1 {

    MongoClient mongoClient;

    @Before
    public void startUp() {
        mongoClient = new MongoClient("192.168.100.20", 27017);
    }

    @Test
    public void createCollection() {
        MongoDatabase testDb = mongoClient.getDatabase("test");
        testDb.createCollection("post");
    }

    @Test
    public void createDocument() {
        MongoDatabase testDb = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = testDb.getCollection("post");
        Document document = new Document("title", "MongoDB").
                append("description", "database").
                append("likes", 100).
                append("by", "Fly");
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        collection.insertMany(documents);
        System.out.println("文档插入成功");
    }

    @Test
    public void findDocument() {
        MongoDatabase testDb = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = testDb.getCollection("post");
        FindIterable<Document> iterable = collection.find();
        MongoCursor<Document> it = iterable.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
