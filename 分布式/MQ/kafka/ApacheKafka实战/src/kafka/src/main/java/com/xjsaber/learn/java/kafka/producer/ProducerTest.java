package com.xjsaber.learn.java.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Kafka producer测试用例
 * @author xjsaber
 */
public class ProducerTest {

    public static void main(String[] args) {
        Properties props = new Properties();
        //必须指定
        props.put("bootstrap.servers", "192.168.33.142:9092,192.168.33.140:9092,192.168.33.146:9092");
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

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
        }

        producer.close();
    }
}
