package com.cyj.mystock.web;

import com.cyj.mystock.service.VisitService;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@CrossOrigin
@Controller
@Log4j2
public class PageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private RestTemplate restTemplate; // HTTP 访问操作类
    @Autowired
    private VisitService visitService;

    @RequestMapping("/")
    public String index(Model model,HttpServletRequest request) {
        model.addAttribute("who", "other");
        visitService.addVisitCount();
        log.info("访问ip：{}",request.getRemoteAddr());
        return "/index";
    }
    @RequestMapping("/me")
    public String indexMe(Model model) {
        model.addAttribute("who", "me");
        return "/index";
    }

}
