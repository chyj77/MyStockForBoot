package com.cyj.mystock.web;

import com.cyj.mystock.bean.FollowStockBean;
import com.cyj.mystock.service.FollowStockService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class FollowStockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FollowStockController.class);


    @Autowired
    private FollowStockService followStockService;

    @RequestMapping("/ccgp/index")
    public String getAll() {
        return  followStockService.getAll();
    }

    @RequestMapping(value = "/ccgp/save", method = RequestMethod.POST)
    public String doSave(@RequestBody FollowStockBean bean) {
        LOGGER.info("{}",bean);
        String providerMsg ="保存成功";
        try {
            followStockService.save(bean);
        }catch (Exception e){
            LOGGER.error("",e);
            e.printStackTrace();
            providerMsg ="保存失败";
        }
        return providerMsg;
    }
    //删除
    @RequestMapping("/ccgp/delete")
    public String delete(HttpServletRequest request) {
        String recId = request.getParameter("recId");
        String providerMsg ="删除成功";
        try{
            followStockService.delete(Long.valueOf(recId));
        }catch (Exception e){
            LOGGER.error("",e);
            e.printStackTrace();
            providerMsg ="删除失败";
        }
        return  providerMsg;
    }
    @RequestMapping("/ccgp/getStock")
    @ResponseBody
    public String ccgpGetStock(HttpServletRequest request) {
        String stockcode = request.getParameter("stockcode");
        String providerMsg = followStockService.getStock(stockcode);
        return providerMsg;
    }

}
