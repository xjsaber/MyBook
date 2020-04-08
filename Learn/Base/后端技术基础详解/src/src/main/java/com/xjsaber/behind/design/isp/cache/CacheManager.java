package com.xjsaber.behind.design.isp.cache;

import jdk.nashorn.internal.runtime.regexp.joni.Config;

public interface CacheManager {

    void reBuild(Config conf);
}
