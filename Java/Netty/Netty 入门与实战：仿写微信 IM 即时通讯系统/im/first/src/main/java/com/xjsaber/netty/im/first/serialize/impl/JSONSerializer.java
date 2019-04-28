package com.xjsaber.netty.im.first.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.xjsaber.netty.im.first.serialize.Serializer;
import com.xjsaber.netty.im.first.serialize.SerializerAlgorithm;

/**
 * @author xjsaber
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
