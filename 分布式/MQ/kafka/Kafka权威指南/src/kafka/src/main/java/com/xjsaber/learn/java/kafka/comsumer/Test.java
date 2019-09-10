package com.xjsaber.learn.java.kafka.comsumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

/**
 * @author xjsaber
 */
public class Test {

    public static void main(String[] args) {

        Properties kafkaProps = new Properties();

        kafkaProps.put("bootstrap.servers", "192.168.33.142:9092,192.168.33.140:9092,192.168.33.146:9092");
        kafkaProps.put("group.id", "CountryCounter");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaConsumer consumer = new KafkaConsumer(kafkaProps);

        consumer.subscribe(new Pattern()"test.*");
    }
}
