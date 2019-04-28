package com.xjsaber.netty.im.first.serialize;

import com.xjsaber.netty.im.first.serialize.impl.JSONSerializer;

/**
 * @author xjsaber
 */
public interface Serializer {

    /**
     * json 序列化
     */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转化成二进制
     * @param object java对象
     * @return byte
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     * @param clazz 类
     * @param bytes 二进制
     * @param <T> 泛型
     * @return 泛型
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);


}
