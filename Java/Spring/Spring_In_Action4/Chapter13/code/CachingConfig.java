package com.xjsaber.spring.cache;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

//import org.springframework.cache.CacheManager;

/**
 * Created by xjsaber on 2017/4/21.
 *
 */
@Configurable
@EnableCaching //启用缓存
public class CachingConfig {

    // 使用EhCache
    @Bean
    public EhCacheCacheManager cacheCacheManager(CacheManager cm) {

        return new EhCacheCacheManager(cm);
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache(){

        EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheFactoryBean.setConfigLocation(
                new ClassPathResource("com/hab")
        );
        return ehCacheFactoryBean;
    }

//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager();
//    }
}
