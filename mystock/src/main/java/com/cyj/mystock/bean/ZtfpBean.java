package com.cyj.mystock.bean;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONArray;
import org.springframework.data.mongodb.core.mapping.Document;

@Log4j2
@Data
@Document(collection="fupan")
public class ZtfpBean {

    private String rq;
    private JSONArray data;

}
