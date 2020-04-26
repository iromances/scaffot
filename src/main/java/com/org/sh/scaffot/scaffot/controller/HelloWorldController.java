package com.org.sh.scaffot.scaffot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @Autowired
    private RedisTemplate redisTemplate;



    @GetMapping
    public String helloWorld(){

        redisTemplate.opsForValue().set("testRedis","成功了");
        Boolean testRedis = redisTemplate.hasKey("testRedis");
        System.out.println(testRedis);

        Object testRedis1 = redisTemplate.opsForValue().get("testRedis");
        System.out.println(testRedis1);


        return "hello world";
    }

}
