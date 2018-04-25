package com.cyj.mystock.mapper;

import com.cyj.mystock.bean.ZtsjBean;

import java.util.List;
import java.util.Map;

public interface ZtsjMapper {

    List<ZtsjBean> getAll();

    ZtsjBean getZtsjfx();

    List<Map> getZtgn();

    void deleteByPrimaryKey(Long recId);

    void insert(ZtsjBean bean);

    void updateByPrimaryKeySelective(ZtsjBean bean);
}
