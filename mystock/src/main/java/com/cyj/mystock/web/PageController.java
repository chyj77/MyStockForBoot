package com.cyj.mystock.web;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

@CrossOrigin
@Controller
public class PageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private RestTemplate restTemplate; // HTTP 访问操作类

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("who", "other");
        return "/index";
    }
    @RequestMapping("/me")
    public String indexMe(Model model) {
        model.addAttribute("who", "me");
        return "/index";
    }

}
