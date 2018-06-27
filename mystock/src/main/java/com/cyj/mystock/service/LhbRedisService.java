package com.cyj.mystock.service;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class LhbRedisService {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询所有的Student信息
     * @return
     */
    public JSONArray findAll(String day){
        Date date1 = new Date();
        String key = "lhb:"+day ;
        String value = redisUtil.get(key);
        JSONArray jsonArray = new JSONArray();
        if(StringUtils.isBlank(value)){
            return jsonArray;
        }
        JSONObject jsonObject = JSONObject.fromObject(value);
        jsonArray.add(jsonObject);
        log.info("获取Redis龙虎榜数据耗时={}毫秒",(new Date().getTime()-date1.getTime()));
//        List<JSONArray> list =  mongoTemplate.findAll(JSONArray.class);
        return jsonArray;
    }

    private String pasrserTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        return sdf.format(date);
    }
}
