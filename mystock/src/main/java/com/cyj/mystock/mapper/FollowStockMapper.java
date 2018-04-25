package com.cyj.mystock.mapper;

import com.cyj.mystock.bean.FollowStockBean;
import com.cyj.mystock.bean.SpmmBean;

import java.util.List;
import java.util.Map;

public interface FollowStockMapper {

    List<FollowStockBean> getAll();

    FollowStockBean getByStockCode(String stockcode);

    void deleteByPrimaryKey(Long recId);

    void insert(FollowStockBean bean);

    void updateByPrimaryKeySelective(FollowStockBean bean);


}
