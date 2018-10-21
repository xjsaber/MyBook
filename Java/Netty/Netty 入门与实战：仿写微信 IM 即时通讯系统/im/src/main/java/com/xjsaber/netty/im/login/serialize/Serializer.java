package com.xjsaber.netty.im.login.serialize;

public interface Serializer {

    byte JSON_SERIALIZER = 1;


    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法 获取具体的序列化算法标识
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
