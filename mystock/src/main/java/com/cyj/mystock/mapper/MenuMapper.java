package com.cyj.mystock.mapper;

import com.cyj.mystock.bean.Menu;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface  MenuMapper {

    List<Menu> getAll(Map map);
}
