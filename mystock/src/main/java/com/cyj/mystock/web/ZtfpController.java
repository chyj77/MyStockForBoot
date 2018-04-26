package com.cyj.mystock.web;

import com.cyj.mystock.service.LhbService;
import com.cyj.mystock.service.ZtfpService;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Log4j2
public class ZtfpController {


    @Autowired
    private ZtfpService ztfpService;

    @RequestMapping("/ztfp/index")
    public String findAll(HttpServletRequest request) {
        String day = request.getParameter("day");
        JSONArray jsonArray = ztfpService.findAll(day);
        return  jsonArray.toJSONString();
    }
}
