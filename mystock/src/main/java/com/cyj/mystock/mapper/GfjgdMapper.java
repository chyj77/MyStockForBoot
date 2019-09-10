package com.cyj.mystock.mapper;

import com.cyj.mystock.bean.FollowStockBean;
import com.cyj.mystock.bean.GfjgdBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface GfjgdMapper {

    List<GfjgdBean> getAll();

    List<Map> queryGfStock();

}
