package com.quesa.mybootproject;

import com.alibaba.fastjson.JSONObject;
import com.quesa.mybootproject.common.cache.RedisCacheManagerFactory;
import com.quesa.mybootproject.common.util.StringUtil;
import com.quesa.mybootproject.module.student.entity.Student;
import com.quesa.mybootproject.module.student.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate; //操作k-v都是字符串的
    @Autowired
    RedisTemplate redisTemplate;    //操作k-v都是对象的
    @Autowired
    RedisTemplate<Object,Object> myRedisTemplate;   //存储的对象在redis中是json格式

    @Autowired
    StudentService studentService;

    @Test
    public void contextLoads() {
    }

    /**
     * 存储后 redis中的数据是序列化后的数据
     */
    @Test
    public void redisSaveTest(){
        //默认如果保存对象 使用jdk序列化机制 将序列化后的数据保存到redis中
        //可以配置修改默认的序列化规则   可改为将数据以json的形式保存
        Student student = studentService.getById(2);
        System.out.println(student);
        System.out.println(student.toJSONObjcet());
        redisTemplate.opsForValue().set(student.getId(),student);
//        myRedisTemplate.opsForValue().set(student.getId(),student.toJSONObjcet());
    }
    @Test
    public void getStudent(){
        Object object = redisTemplate.opsForValue().get("2");
        System.out.println(object);
    }


    /**
     * 将对象存为json
     */
    @Test
    public void redisSaveObjectTest(){
        //默认如果保存对象 使用jdk序列化机制 将序列化后的数据保存到redis中
        //可以配置修改默认的序列化规则   可改为将数据以json的形式保存
        Student student = studentService.getById(1);
        System.out.println(student);
        System.out.println(student.toJSONObjcet());
        myRedisTemplate.opsForValue().set(student.getId(),student);
//        myRedisTemplate.opsForValue().set(student.getId(),student.toJSONObjcet());
    }
    @Test
    public void redisGetObjectTest(){
        Object object =  myRedisTemplate.opsForValue().get("1");
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(object);
        if (!StringUtil.isEmpty(object)){
            System.out.println(jsonObject);
            System.out.println(jsonObject.getString("name"));
            System.out.println(jsonObject.getString("ct"));
        }else {
            System.out.println("未查询到数据...");
        }
    }


    @Test
    public void cacheManagerTest(){
        Student student = studentService.getById("3");
        System.out.println(student);
        RedisCacheManagerFactory.getInstance().set(student.getId(),student);
    }
    @Test
    public void cacheManagerGetTest(){
        Object object = RedisCacheManagerFactory.getInstance().get("3");

        System.out.println((Student)object);
    }



}
