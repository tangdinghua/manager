package com.trip.base.util;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yk on 2016/5/20.
 */
public class ResultUtil {

    public static Map<String,Object> getResultMap(String code, String message){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("c",code);
        resultMap.put("m",message);
        resultMap.put("b",new HashMap<String,Object>());
        return resultMap;
    }
    public static void putHead(Map<String,Object> resultMap,String key,Object value){
        Map<String,Object> body = (Map<String,Object>)resultMap.get("h");
        body.put(key,value);
    }
    public static void putKey(Map<String,Object> resultMap,String key,Object value){
        Map<String,Object> body = (Map<String,Object>)resultMap.get("b");
        body.put(key,value);
    }

    public static String toJson(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
