package com.xjsaber.learn.kafka;

//import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

/**
 * @author xjsaber
 */
public class KafkaProducer {

    private Producer<String, String> producer;

    public KafkaProducer(){
        Properties prop = new Properties();
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(prop);
    }

//    public void send() {
//        producer.send(new ProducerRecord<String, String>(), new Callback() {
//            @Override
//            public void onCompletion(RecordMetadata metadata, Exception exception) {
//
//            }
//        });
//    }
}
