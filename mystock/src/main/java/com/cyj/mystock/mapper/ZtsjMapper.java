package com.cyj.mystock.mapper;

import com.cyj.mystock.bean.ZtsjBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface ZtsjMapper {

    List<ZtsjBean> getAll();

    ZtsjBean getZtsjfx();

    List<Map> getZtgn();

    void deleteByPrimaryKey(Long recId);

    void insert(ZtsjBean bean);

    void updateByPrimaryKeySelective(ZtsjBean bean);

    ZtsjBean getOne(@Param("rq") String rq);
}
