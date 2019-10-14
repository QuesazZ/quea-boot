package com.quesa.mybootproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Autowired
    @Bean(name = "jedis.pool")
    public JedisPool getJedisPool(@Qualifier("jedis.pool.config") JedisPoolConfig config,
                                  @Value("${jedis.pool.host}") String host,
                                  @Value("${jedis.pool.port}") int port,
                                  @Value("${jedis.pool.timeout}")int timeout,
                                  @Value("${jedis.pool.password}")String password) {
        return new JedisPool(config, host, port,timeout,password);
    }

    @Bean(name = "jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig(@Value("${jedis.pool.config.maxTotal}") int maxTotal,
                                           @Value("${jedis.pool.config.maxIdle}") int maxIdle,
                                           @Value("${jedis.pool.config.maxWaitMillis}") int maxWaitMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }


    /***
     * 修改redis的默认序列化器  将对象存为json
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<Object,Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //修改默认的redis序列化器
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);

        redisTemplate.setDefaultSerializer(serializer);
        return redisTemplate;
    }


}
