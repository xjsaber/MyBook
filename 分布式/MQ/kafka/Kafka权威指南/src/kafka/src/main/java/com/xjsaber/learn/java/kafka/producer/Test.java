package com.xjsaber.learn.java.kafka.producer;

import java.util.Properties;

public class Test {

    private static Properties kafkaProps = new Properties();

    public static void main(String[] args) {
        kafkaProps.put("boostrap.servers", "broker1:9092, broker:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    }
}
