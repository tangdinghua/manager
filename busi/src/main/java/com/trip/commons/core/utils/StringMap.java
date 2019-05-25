package com.trip.commons.core.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;

/**
 * 字符串Map
 *
 * @author fqh
 * @create 2016-12-10 3:53
 */
public class StringMap extends HashMap<String, Object> {

    /**
     * 获取字符串值
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue) {
        Object value = get(key);
        if(value == null) {
            return defaultValue;
        }
        if(value instanceof String) {
            return (String) value;
        }
        return value + "";
    }

    /**
     * 获取字符串
     * @param key
     * @return
     */
    public String getString(String key) {
        return getString(key, null);
    }

    /**
     * 获取整数
     * @param key
     * @param defaultValue
     * @return
     */
    public Integer getInteger(String key, int defaultValue) {
        Object value = get(key);
        if(value == null) {
            return defaultValue;
        }
        if(value instanceof Integer) {
            return (Integer) value;
        }
        return NumberUtils.toInt(value + "", defaultValue);
    }

    /**
     * 获取整数
     * @param key
     * @return
     */
    public Integer getInteger(String key) {
        return getInteger(key, 0);
    }

    /**
     * 设置值
     * @param key
     * @param value
     * @return
     */
    public StringMap putObject(String key, Object value) {
        put(key, value);
        return this;
    }

}
