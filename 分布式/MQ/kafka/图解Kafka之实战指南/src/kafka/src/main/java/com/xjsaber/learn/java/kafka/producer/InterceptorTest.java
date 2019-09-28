package com.xjsaber.learn.java.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author xjsaber
 */
public class InterceptorTest {

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

        List<String> interceptorList = new ArrayList<>();
        // interceptor1
        interceptorList.add("com.xjsaber.learn.java.kafka.producer.CounterInterceptor");
        // interceptor2
        interceptorList.add("com.xjsaber.learn.java.kafka.producer.TimeStampPrependerInterceptor");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorList);

        String topic = "test-topic";
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++){
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, "message " + i);
            try {
                producer.send(record).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        producer.close();
    }
}
