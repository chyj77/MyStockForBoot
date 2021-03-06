package com.cyj.mystock.web;

import com.cyj.mystock.service.LhbRedisService;
import com.cyj.mystock.service.LhbService;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LhbController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LhbController.class);

    @Autowired
    private LhbRedisService lhbService;

    @RequestMapping("/lhb/index")
    public String findAll(HttpServletRequest request) {
        String day = request.getParameter("day");
        JSONArray jsonArray = lhbService.findAll(day);
        return  jsonArray.toJSONString();
    }
}
