package com.example.contoller;

import com.example.entity.Users;
import com.example.util.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author DQ
 * Date 2020/5/22
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    private final RedisUtil redisUtil;

    public RedisController(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }


    @RequestMapping("set")
    public boolean redisset(String key, String value){
        Users users =new Users();
        users.setId(2);
        users.setName("dengqiu");

        //return redisUtil.set(key,userEntity,ExpireTime);

        return redisUtil.set(key,users);
    }

    @RequestMapping("get")
    public Object redisget(String key){
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }

}
