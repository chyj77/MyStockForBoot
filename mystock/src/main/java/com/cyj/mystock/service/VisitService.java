package com.cyj.mystock.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class VisitService {
    @Autowired
    private RedisUtil redisUtil;
    private String key = "visit";

    public void addVisitCount(){
        boolean exists = redisUtil.exists(key);
        if(exists){
            String counts = redisUtil.get(key);
            Integer count = Integer.valueOf(counts);
            count = count + 1;
            redisUtil.set(key,count);
        }else {
            redisUtil.set(key,"1");
        }
    }
}
