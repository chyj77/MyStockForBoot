package com.cyj.mystock;


import com.cyj.mystock.config.DxjlPool;
import com.cyj.mystock.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * 这里通过设定value的值来指定执行顺序
 */
@Component
@Order(value = 1)
public class MyApplicationRunner implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplicationRunner.class);

    @Autowired
    private ZtsjInfoService ztsjInfoService;
    @Autowired
    private SpmmInfoService spmmInfoService;
    @Autowired
    private FollowStockService followStockService;
    @Autowired
    private WebsocketService websocketService;
    @Autowired
    private MenuService menuService;

    @Override
    public void run(ApplicationArguments var1) throws Exception{
        LOGGER.info("MyApplicationRunner2!");
        DxjlPool.clear();
        websocketService.initGetStock();
        menuService.getAll();
        ztsjInfoService.setZtgn();
        ztsjInfoService.setAll();
        spmmInfoService.setLuoji();
        spmmInfoService.setStock();
        followStockService.setRedisData();
    }
}
