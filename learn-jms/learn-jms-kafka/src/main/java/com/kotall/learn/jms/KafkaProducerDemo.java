package com.kotall.learn.jms;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * desc:
 *
 * @author zpwang
 * @create 2019/3/18 17:51
 * @since 1.0.0
 */
public class KafkaProducerDemo {

    public static void main(String[] args) {
        Properties props = new Properties();
        // 服务器ip:端口号，集群用逗号分隔
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.100.11:9092,192.168.100.12:9092");
        // key序列化指定类
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // value序列化指定类
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        // 向test_topic发送hello,kafka
        producer.send(new ProducerRecord<>("test_topic", "hello, kafka"));
        producer.close();
    }
}
