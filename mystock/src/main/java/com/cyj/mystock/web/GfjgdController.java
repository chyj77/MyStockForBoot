package com.cyj.mystock.web;

import com.cyj.mystock.bean.FollowStockBean;
import com.cyj.mystock.service.FollowStockService;
import com.cyj.mystock.service.GfjgdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
public class GfjgdController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GfjgdController.class);

    @Autowired
    private GfjgdService gfjgdService;

    @RequestMapping(value = "/gfjgd/index")
    public String getAll() {
        Date date1 = new Date();
        String result =  gfjgdService.getAll();
        LOGGER.info("查找股票交割单耗时={}毫秒",(System.currentTimeMillis()-date1.getTime()));
        return result;
    }

    @RequestMapping(value = "/gfjgd/fx", method = RequestMethod.GET)
    public String queryGfStock() throws Exception{
        return gfjgdService.queryGfStock();
    }

}
