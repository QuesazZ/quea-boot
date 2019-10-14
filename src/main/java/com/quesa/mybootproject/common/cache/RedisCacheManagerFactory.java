package com.quesa.mybootproject.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缓存工厂类
 */
public class RedisCacheManagerFactory {

    private static Logger logger = LoggerFactory.getLogger(RedisCacheManagerFactory.class);

    public static CacheManager getInstance() {
//        CacheManager cacheManager = getCacheManager("redis");
        CacheManager cacheManager = RedisClient.getInstance();;

        return cacheManager;
    }

/*
    private static CacheManager getCacheManager(String name) {
        if ("redis".equals(name)) {
            return RedisClient.getInstance();
        } else {
            logger.info("未指定缓存");
        }
        return null;
    }
*/


}
