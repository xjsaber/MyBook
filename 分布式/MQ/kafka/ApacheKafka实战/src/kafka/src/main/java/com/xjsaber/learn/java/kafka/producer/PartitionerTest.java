package com.xjsaber.learn.java.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class PartitionerTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.33.142:9092,192.168.33.140:9092,192.168.33.146:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        String topic = "test-topic";
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);
//        ProducerRecord nonKeyRecord = new ProducerRecord(topic, "non-key record");
//        ProducerRecord auditRecord = new ProducerRecord(topic, "audit", "audit record");
//        ProducerRecord nonAuditRecord =
    }
}
