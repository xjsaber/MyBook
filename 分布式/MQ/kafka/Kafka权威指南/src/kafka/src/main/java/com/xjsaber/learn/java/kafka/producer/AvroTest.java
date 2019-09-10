package com.xjsaber.learn.java.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class AvroTest {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.server", "");
        props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("schema.registry.url", "schemaUrl");
        String topic = "test-kafka";
        Producer<String, Customer> producer = new KafkaProducer<String, Customer>(props);
        // 不断生成事件
        while(true){
            Customer customer = CustomerGen
        }
    }
}
