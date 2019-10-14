package com.quesa.mybootproject.config;

import com.quesa.mybootproject.common.cache.RedisCacheManagerFactory;
import com.quesa.mybootproject.common.cache.RedisClient;
import com.quesa.mybootproject.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;


/**
 *
 */
@Component
public class ApplicationStartListener implements ApplicationListener<ApplicationReadyEvent> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
        //初始化Redis连接
        try {
            Jedis jedis = RedisClient.getInstance().getResource();
            if (null!=jedis) {
                logger.info("redis连接成功!");
            } else {
                logger.info("redis连接失败...");
            }
        } catch (Exception e) {
            StringUtil.getStackMsg(e);
            logger.info("redis连接失败...");
        }
    }


}
