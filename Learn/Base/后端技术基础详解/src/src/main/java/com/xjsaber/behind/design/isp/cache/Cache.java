package com.xjsaber.behind.design.isp.cache;

public interface Cache {

    Object get(Object key);
    void put(Object key, Object value);
    void delete(Object key);
}
