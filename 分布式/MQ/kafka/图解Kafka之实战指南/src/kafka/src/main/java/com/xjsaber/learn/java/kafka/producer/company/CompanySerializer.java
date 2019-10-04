package com.xjsaber.learn.java.kafka.producer.company;

import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 自定义序列化器CompanySerializer<Company>
 * @author xjsaber
 */
public class CompanySerializer implements Serializer<Company> {

    @Override
    public void configure(Map configs, boolean isKey){}

    @Override
    public byte[] serialize(String topic, Company data) {
        if (data == null){
            return null;
        }
        byte[] name, address;
        if (data.getName() != null){
            name = data.getName().getBytes(StandardCharsets.UTF_8);
        } else {
            name = new byte[0];
        }
        if (data.getAddress() != null){
            address = data.getAddress().getBytes(StandardCharsets.UTF_8);
        } else {
            address = new byte[0];
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 + 4 + name.length + address.length);
        byteBuffer.putInt(name.length);
        byteBuffer.put(name);
        byteBuffer.putInt(address.length);
        byteBuffer.put(address);
        return byteBuffer.array();
    }

    @Override
    public void close(){

    }
}
