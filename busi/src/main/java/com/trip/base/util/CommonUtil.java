package com.trip.base.util;


import java.util.Map;
import java.util.Random;

/**
 * Created by yk on 2016/10/23.
 */
public class CommonUtil {

    public static String generateCode(){
        Random random  = new Random();
        return String.valueOf(random.nextInt(8999)+1000);
    }

    public static String getMobile(Map<String,Object> head){
        return (String)head.get("mobile");
    }


}
