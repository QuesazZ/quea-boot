package com.quesa.mybootproject.common.cache;


import java.util.Set;

/**
 * 缓存接口
 */
public interface CacheManager {

    /**
     * 永久保存键值
     *
     * @param key 键
     * @param obj 值
     * @return
     */
    String set(String key, Object obj);

    /**
     * 保存键值并指定存储时长，单位秒。
     *
     * @param key        键
     * @param obj        值
     * @param timeSecond 缓存时间（秒），0 为不超时
     * @return
     */
    String set(String key, Object obj, int timeSecond);


    /**
     * 根据键获取值
     *
     * @param key 键
     * @param <T> 返回存入值
     * @return
     */
    <T> T get(String key);

    /**
     * 根据键判断缓存是否存在
     *
     * @param key 键
     * @return
     */
    boolean exists(String key);

    /**
     * 注入指定key生存时间
     *
     * @param key        值
     * @param timeSecond 生存时间（秒）
     * @return
     */
    long expire(String key, int timeSecond);

    /**
     * 根据键删除对应键值
     *
     * @param key 键
     * @return
     */
    long deleteByKey(String key);

    String getStringHash(String key);

    Long setStringHash(String key, String value, int timeSeconds);

    Long delHashValue(String key);

    long setSet(String key, Set<String> value, int cacheSeconds);

    Set<String> getSet(String key);

    long setSetAdd(String key, String... value);

    String getObjectListNew(String key);

    long deleteByKeyPrefix(String keyPrefix);

    long incr(String key);
}
