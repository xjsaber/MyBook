package com.xjsaber.learn.java.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.requests.ProduceRequest;

import java.util.Properties;
import java.util.concurrent.Future;

public class Test {

    public static void main(String[] args) {

        Properties kafkaProps = new Properties();

        kafkaProps.put("bootstrap.servers", "192.168.33.142:9092,192.168.33.140:9092,192.168.33.146:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer producer = new KafkaProducer(kafkaProps);

        ProducerRecord<String, String> record = new ProducerRecord<String, String>("test-kafka", "Precision Products", "France");
        try {
            Future future =producer.send(record);
            System.out.println(future.get());
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
