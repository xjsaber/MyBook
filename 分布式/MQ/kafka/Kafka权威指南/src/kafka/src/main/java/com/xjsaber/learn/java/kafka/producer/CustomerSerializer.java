package com.xjsaber.learn.java.kafka.producer;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomerSerializer implements Serializer<Customer> {

    @Override
    public void configure(Map configs, boolean isKey){
        // 不做任何配置
    }

    @Override
    public byte[] serialize(String s, Customer customer) {
        return new byte[0];
    }


}
