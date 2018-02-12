package com.online.college.common.util;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *
* @Description: JsonUtil
* @author cmazxiaoma
* @date 2018-02-08 10:50
* @version V1.0
 */
public class JsonUtil {

    private static  ObjectMapper mapper;

    static {
        mapper=new ObjectMapper();
    }

    public static String toJson(Object obj) throws IOException {
        String json = mapper.writeValueAsString(obj);
        return json;
    }
}
