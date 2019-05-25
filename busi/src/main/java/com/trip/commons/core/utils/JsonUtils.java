package com.trip.commons.core.utils;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * JSON工具类
 *
 * @author fqh
 * @create 2016-12-12 14:53
 */
public class JsonUtils {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 处理整数变成Double的问题。
     */
    private static class DoubleSerializer implements JsonSerializer<Double> {

        @Override
        public JsonElement serialize(Double value, Type type, JsonSerializationContext context) {
            Integer intValue = value.intValue();
            if(new Double(intValue).equals(value)) {
                return new JsonPrimitive(intValue);
            }
            return new JsonPrimitive(value);
        }
    }

    public static Gson getGson(String dateFormat) {
        if(dateFormat == null) {
            dateFormat = DEFAULT_DATE_FORMAT;
        }
        return new GsonBuilder().disableHtmlEscaping().serializeNulls().registerTypeHierarchyAdapter(Double.class, new DoubleSerializer()).setDateFormat(dateFormat).create();
    }

    public static Gson getGson() {
        return getGson(DEFAULT_DATE_FORMAT);
    }

    /**
     * 把对象变成json字符串。
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return getGson().toJson(obj);
    }

    /**
     * 把对象变成json字符串
     * @param obj
     * @param dateFormat 字符串，默认为：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String toJson(Object obj, String dateFormat) {
        return getGson(dateFormat).toJson(obj);
    }

    public static JsonObject fromJson(String value) {
        return getGson().fromJson(value, JsonObject.class);
    }

    public static <T> T fromJson(String value, Class<T> clazz) {
        return getGson().fromJson(value, clazz);
    }

}
