
package com.quesa.mybootproject.common.cache;

import com.quesa.mybootproject.common.util.ObjectUtils;
import com.quesa.mybootproject.common.util.SpringContextHolder;
import com.quesa.mybootproject.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Jedis Cache 工具类
 */
public class RedisClient implements CacheManager {


    private static Logger logger = LoggerFactory.getLogger(RedisClient.class);

    private static RedisClient redisClient;
    private static JedisPool jedisPool = SpringContextHolder.getBean(JedisPool.class);
    private static ReentrantLock lock = new ReentrantLock();

    public static RedisClient getInstance() {
        if (redisClient == null) {
            lock.lock();
            if (redisClient == null) {
                redisClient = new RedisClient();
            }
            lock.unlock();
        }
        return redisClient;
    }

    /**
     * 获取资源
     *
     * @return
     * @throws JedisException
     */
    public Jedis getResource() throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (JedisException e) {
            logger.warn("getResource.", e);
            returnBrokenResource(jedis);
            throw e;
        }
        return jedis;
    }

    /**
     * 归还资源
     *
     * @param jedis
     */
    public void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
//            jedisPool.returnBrokenResource(jedis);
        }
    }

    /**
     * 释放资源
     *
     * @param jedis
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
//            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 获取byte[]类型Key
     *
     * @param object
     * @return
     */
    public byte[] getBytesKey(Object object) {
        if (object instanceof String) {
            return StringUtil.getBytes((String) object);
        } else {
            return ObjectUtils.serialize(object);
        }
    }

    /**
     * Object转换byte[]类型
     *
     * @param object
     * @return
     */
    public byte[] toBytes(Object object) {
        return ObjectUtils.serialize(object);
    }

    /**
     * byte[]型转换Object
     *
     * @param bytes
     * @return
     */
    public Object toObject(byte[] bytes) {
        if (null == bytes) {
            return null;
        }
        return ObjectUtils.unserialize(bytes);
    }

    /**
     * 永久保存键值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    @Override
    public String set(String key, Object value) {
        String result = null;
        Jedis jedis = null;
        if (StringUtil.isBlank(key)) {
            return "0";
        }
        try {
            jedis = getResource();
            result = jedis.set(getBytesKey(key), toBytes(value));
        } catch (Exception e) {
            logger.warn("setObject {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 永久保存键值,并设置缓存时间（秒）
     *
     * @param key         键
     * @param value       值
     * @param timeSeconds 缓存时间（秒），0 为不超时
     * @return
     */
    @Override
    public String set(String key, Object value, int timeSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.set(getBytesKey(key), toBytes(value));
            if (timeSeconds != 0) {
                jedis.expire(key, timeSeconds);
            }
        } catch (Exception e) {
            logger.warn("set {} = {}", key, value, timeSeconds, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 根据键获取值
     *
     * @param key 键
     * @return
     */
    @Override
    public <T> T get(String key) {
        if (StringUtil.isBlank(key)) {
            return null;
        }
        T result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = (T) toObject(jedis.get(getBytesKey(key)));
        } catch (Exception e) {
            logger.warn("getObject {} = {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 根据键判断缓存是否存在
     *
     * @param key 键
     * @return
     */
    @Override
    public boolean exists(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.exists(getBytesKey(key));
        } catch (Exception e) {
            logger.warn("exists {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 根据键设置缓存生存时间（秒）
     *
     * @param key        键
     * @param timeSecond 生存时间（秒）
     * @return
     */
    @Override
    public long expire(String key, int timeSecond) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (timeSecond != 0) {
                jedis.expire(key, timeSecond);
            }
        } catch (Exception e) {
            logger.warn("setSet {} = {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 根据键删除缓存
     *
     * @param key 键
     * @return
     */
    @Override
    public long deleteByKey(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                result = jedis.del(getBytesKey(key));
            }
        } catch (Exception e) {
            logger.warn("delObject {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    @Override
    public String getStringHash(String key) {

        Jedis jedis = null;
        String result = null;
        try {
            jedis = getResource();
            result = jedis.hget(key, "redis");
        } catch (Exception e) {
            logger.warn("getObjectList {} = {}", key, "redis", e);
        } finally {
            returnResource(jedis);
        }

        return result;
    }

    @Override
    public Long setStringHash(String key, String value, int timeSeconds) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = getResource();
            result = jedis.hset(key, "redis", value);
            if (timeSeconds != 0) {
                jedis.expire(key, timeSeconds);
            }
        } catch (Exception e) {
            logger.warn("getObjectList {} = {}", key, "redis", e);
        } finally {
            returnResource(jedis);
        }

        return result;
    }

    /**
     * 删除缓存
     */
    @Override
    public Long delHashValue(String key) {
        Jedis jedis = null;
        Long result = Long.valueOf(0);
        try {
            jedis = getResource();
            result = jedis.hdel(key, "redis");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设置Set缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    @Override
    public long setSet(String key, Set<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.sadd(key, (String[]) value.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("setSet {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    @Override
    public Set<String> getSet(String key) {
        Set<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.smembers(key);
            }
        } catch (Exception e) {
            logger.warn("getSet {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 向Set缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    @Override
    public long setSetAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.sadd(key, value);
            //logger.debug("setSetAdd {} = {}", key, value);
        } catch (Exception e) {
            logger.warn("setSetAdd {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取List缓存(自定义) json串
     *
     * @param key 键
     * @return 值
     */
    @Override
    public String getObjectListNew(String key) {
        List<Object> value = null;
        Jedis jedis = null;
        String s = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
//                List<Object> list = jedis.lrange(key, 0, -1);
                s = jedis.get(key);
            }
        } catch (Exception e) {
            logger.warn("getObjectList {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return s;
    }

    @Override
    public long deleteByKeyPrefix(String keyPrefix) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<String> keySet = jedis.keys(keyPrefix+"*");
            for(String key : keySet){
                result += jedis.del(key);
            }
        } catch (Exception e) {
            logger.warn("deleteByKeyPrefix {}", keyPrefix, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    @Override
    public long incr(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.incr(key);
        } catch (Exception e) {
            logger.warn("incr failed {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }
}
