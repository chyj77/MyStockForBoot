package com.cyj.mystock.web;

import com.cyj.mystock.bean.ZtsjBean;
import com.cyj.mystock.service.ZtsjInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
public class ZtsjController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZtsjController.class);

    @Autowired
    private ZtsjInfoService service;

    @RequestMapping("/ztsj/ztgn")
    public String ztgn() {
        return  service.getZtgn();
    }
    @RequestMapping("/ztsj/index")
    public String ztsj() {
        return  service.getAll();
    }
    @RequestMapping("/ztsj/fx")
    public String ztsjFx() {
        return  service.getZtsjFx();
    }
    @RequestMapping(value = "/ztsj/save", method = RequestMethod.POST)
    public String ztsjSave(@RequestBody ZtsjBean ztsj) {
        LOGGER.info("{}",ztsj);
        String providerMsg ="保存成功";
        try {
            service.save(ztsj);
        }catch (Exception e){
            LOGGER.error("",e);
            e.printStackTrace();
            providerMsg ="保存失败";
        }
        return providerMsg;
    }
    //删除
    @RequestMapping("/ztsj/delete")
    public String delete(HttpServletRequest request) {
        String recId = request.getParameter("recId");
        String providerMsg ="删除成功";
        try{
            service.delete(Long.valueOf(recId));
        }catch (Exception e){
            LOGGER.error("",e);
            e.printStackTrace();
            providerMsg ="删除失败";
        }
        return  providerMsg;
    }
}
