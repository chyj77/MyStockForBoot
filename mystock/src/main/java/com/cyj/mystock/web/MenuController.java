package com.cyj.mystock.web;

import com.cyj.mystock.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);


    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu/index")
    public String menu(HttpServletRequest request) {
        String who = request.getParameter("who");
        return  menuService.getMenu(who);
    }


}
