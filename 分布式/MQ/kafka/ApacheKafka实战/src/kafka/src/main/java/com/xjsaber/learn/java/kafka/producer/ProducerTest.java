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
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i));
            // 异步发送
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        // 消息发送成功
                    } else {
                        if (e instanceof RetriableException) {
//                            处理可重试瞬时异常
                        } else {
//                            处理不可重试瞬时异常
                        }
                        // 执行错误处理逻辑
                    }
                }
            });
            // 同步发送
//            Future future = producer.send(record);
//            try {
//                future.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }

        producer.close();
    }
}
