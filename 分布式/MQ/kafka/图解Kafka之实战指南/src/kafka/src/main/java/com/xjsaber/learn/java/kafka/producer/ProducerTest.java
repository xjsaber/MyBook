package com.xjsaber.learn.java.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.RetriableException;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Kafka producer测试用例
 * @author xjsaber
 */
public class ProducerTest {

    private static String topic = "test-topic";

    public static void main(String[] args) {
        Properties props = new Properties();
        //必须指定
        props.put("bootstrap.servers", "192.168.33.142:9092,192.168.33.196:9092,192.168.33.146:9092");
        //必须指定
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //必须指定
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "-1");
        props.put("retries", 3);
        props.put("batch.size", 323840);
        props.put("linger.ms", 10);
        props.put("buffer.memory", 33554432);
        props.put("max.block.ms", 3000);

        Producer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello kafka");

        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();

    }
}
