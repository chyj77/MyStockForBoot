package com.cyj.mystock.web;

import com.cyj.mystock.bean.SpmmBean;
import com.cyj.mystock.bean.ZtsjBean;
import com.cyj.mystock.service.DfbService;
import com.cyj.mystock.service.SpmmInfoService;
import com.cyj.mystock.service.ZfbService;
import com.cyj.mystock.service.ZtsjInfoService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SpmmController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpmmController.class);

    @Autowired
    private SpmmInfoService service;
    @Autowired
    private DfbService dfbService;
    @Autowired
    private ZfbService zfbService;

    @RequestMapping("/spmm/luoji")
    public String luoji() {
        return  service.getLuoji();
    }
    @RequestMapping("/spmm/stocks")
    public String stocks() {
        return  service.getStock();
    }
    @RequestMapping("/spmm/getStock")
    public String getStock(HttpServletRequest request) {
        String stockcode = request.getParameter("stockcode");
        return  service.getStock(stockcode);
    }
    @RequestMapping("/spmm/index")
    public String spmm() {
        return  service.getAll();
    }
    @RequestMapping(value = "/spmm/save", method = RequestMethod.POST)
    public String spmmSave(@RequestBody SpmmBean bean) {
        LOGGER.info("{}",bean);
        String providerMsg ="保存成功";
        try {
            service.save(bean);
        }catch (Exception e){
            LOGGER.error("",e);
            e.printStackTrace();
            providerMsg ="保存失败";
        }
        return providerMsg;
    }
    //删除
    @RequestMapping("/spmm/delete")
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

    @RequestMapping("/spmm/fx")
    public String querySpmmFx() throws Exception {
        return  service.querySpmmFx();
    }

    @RequestMapping("/spmm/zdt")
    public String queryZdt() throws Exception {
        return  service.getDayzdt();
    }
    @RequestMapping("/spmm/zdfb")
    public void queryZdfb() throws Exception {
        zfbService.run();
        dfbService.run();
    }

}
