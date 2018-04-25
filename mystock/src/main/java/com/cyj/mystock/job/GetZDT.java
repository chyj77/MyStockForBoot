package com.cyj.mystock.job;

import com.cyj.mystock.bean.ZdtBean;
import com.cyj.mystock.config.Const;
import com.cyj.mystock.queue.QueueSender;
import com.cyj.mystock.service.GetZDTService;
import com.cyj.mystock.service.SpmmInfoService;
import com.cyj.mystock.utils.MyStringUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Component
@Log4j2
public class GetZDT {

    private final long SECOND = 60 * 1000L;

    private Timer timer = new Timer(true);

    private TimerTask timerTask = null;
    @Autowired
    private GetZDTService getZDTService;


    private boolean flag;

    public boolean start() throws Exception {
        if (timerTask == null) {
            timer.schedule(timerTaskInstance(), 0L, SECOND);
        }
        return flag;
    }

    private TimerTask timerTaskInstance(){
        if(timerTask==null){
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    getZDTService.run(flag);
                    if (flag) {
                        timerTask.cancel();
                        timerTask = null;
                    }
                }
            };
        }
        return timerTask;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
