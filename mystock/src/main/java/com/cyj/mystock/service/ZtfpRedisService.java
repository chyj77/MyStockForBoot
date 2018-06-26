package com.cyj.mystock.service;

import com.cyj.mystock.bean.ZtfpBean;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Log4j2
public class ZtfpRedisService {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询所有的复盘信息
     * @return
     */
    public net.sf.json.JSONArray findAll(String day){
        Date date1 = new Date();

        String key = "fupan:"+day ;
        String value = redisUtil.get(key);
        if(StringUtils.isBlank(value)){
            return new net.sf.json.JSONArray();
        }
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(value);
        String data = jsonObject.getString("data");
        List<ZtfpBean> list = new ArrayList<ZtfpBean>();
        ZtfpBean ztfpBean = new ZtfpBean();
        ztfpBean.setRq(day);
        ztfpBean.setData(net.sf.json.JSONArray.fromObject(data));
        list.add(ztfpBean);
        log.info("获取Redis复盘数据耗时={}毫秒",(System.currentTimeMillis()-date1.getTime()));
        net.sf.json.JSONArray jsonArray =net.sf.json.JSONArray.fromObject(list);

        return jsonArray;
    }

    public void saveFpbj(ZtfpBean ztfpBean)throws Exception{
        String key = "fupan:"+ztfpBean.getRq();
        JSONObject value = JSONObject.fromObject(ztfpBean);
        redisUtil.set(key,value.toString());
    }


}
