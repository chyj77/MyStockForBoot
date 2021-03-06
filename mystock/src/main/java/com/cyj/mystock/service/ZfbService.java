package com.cyj.mystock.service;

import com.cyj.mystock.config.Const;
import com.cyj.mystock.queue.QueueSender;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class ZfbService {
    @Autowired
    private QueueSender queueSender;

    private final String URL="http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?" +
            "page=1&num=100&sort=changepercent&asc=0&node=hs_a&symbol=&_s_r_a=init";

    public String getZfb(){
        //        log.info("获取涨幅榜的时间：{}",new Date());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result ="";
        try {
            HttpGet request = new HttpGet(URL);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(15000)
                    .setConnectionRequestTimeout(30000).build();
            request.setConfig(requestConfig);
            CloseableHttpResponse response = httpclient.execute(request);
            String content = EntityUtils.toString(response.getEntity());
            JSONArray jsonArray = JSONArray.fromObject(content);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(Const.KEY,Const.ZFB);
            jsonObject.put("data",jsonArray);
            result = jsonObject.toString();
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取涨幅榜异常：",e);
        }
        return result;
    }
    public void run(){
        String queueName = "zfb";
        queueSender.send(queueName,getZfb());
    }
}
