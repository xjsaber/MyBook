package com.xjsaber.learn.java.kafka.producer;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author xjsaber
 */
public class CounterInterceptor implements ProducerInterceptor<String, String> {

    private int errorCount = 0;
    private int successCount = 0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e == null){
            successCount++;
        } else {
            errorCount++;
        }
    }

    @Override
    public void close() {
        System.out.println("Successful sent: " + successCount);
        System.out.println("Failed sent: " + errorCount);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
