package com.xjsaber.learn.java.kafka.producer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @author xjsaber
 */
public class CustomerSerializer implements Serializer<Customer> {

    @Override
    public void configure(Map configs, boolean isKey){
        // 不做任何配置
    }

    @Override
    /**
     * Customer对象被序列化成：
     * 表示customerID的4字节整数
     * 表示customerName长度的4字节整数
     * 表示customerName的N个字节
     */
    public byte[] serialize(String topic, Customer customer) {
        try {
            byte[] serializedName;
            int stringSize;
            if (customer == null) {
                return null;
            } else {
                if (customer.getName() != null) {
                    serializedName = customer.getName().getBytes("UTF-8");
                    stringSize = serializedName.length;
                } else {
                    serializedName = new byte[0];
                    stringSize = 0;
                }
            }
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringSize);
            buffer.putInt(Integer.parseInt(customer.getId()));
            buffer.putInt(stringSize);
            buffer.put(serializedName);

            return buffer.array();
        }
        catch (Exception e){
            throw new SerializationException("Error when serializing Customer to byte[]" + e);
        }
    }

    @Override
    public void close(){
        // 不需要关闭任何东西
    }
}
