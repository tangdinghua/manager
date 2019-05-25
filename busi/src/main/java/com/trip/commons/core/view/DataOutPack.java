package com.trip.commons.core.view;

import com.trip.commons.core.exception.ICodeExcetpion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 输出数据包
 *
 * @author fqh
 * @create 2016-12-08 23:35
 */
public class DataOutPack extends HashMap<String, Object> {

    private static Logger logger = LoggerFactory.getLogger(DataOutPack.class);

    private String code = "0";

    private String message = "";

    public DataOutPack() {
        this("0", "");
    }

    public DataOutPack(Map<String, Object> map) {
        this.setData(map);
    }

    public DataOutPack(String code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

    public DataOutPack(Throwable t) {
        this();
        this.setException(t);
    }

    public DataOutPack(Object data) {
        this();
        this.setData(data);
    }

    public DataOutPack(String code, String msg, Object data) {
        this(code, msg);
        this.setData(data);
    }

    public DataOutPack setCode(String code) {
        this.code = code;
        this.put("code", code);
        return this;
    }

    public DataOutPack setMsg(String msg) {
        this.message = msg;
        this.put("msg", message);
        return this;
    }

    public DataOutPack setException(Throwable e) {
        Throwable t = e;
        while(t.getCause() != null && !t.getCause().equals(t)) {
            t = t.getCause();
        }
        if(e instanceof ICodeExcetpion) {
            this.setCode(((ICodeExcetpion) e).getCode());
            this.setMsg(t.getMessage());
        } else {
            logger.error(e.getMessage(), e);
            this.setCode("8000");
            this.setMsg("系统忙，请稍后再试");
        }
        return this;
    }

    public DataOutPack setData(Map<String, Object> data) {
        if(data.containsKey("code")) {
            this.setCode((String) data.remove("code"));
        }
        if(data.containsKey("msg")) {
            this.setMsg((String) data.remove("msg"));
        }
        this.putAll(data);
        return this;
    }

    public DataOutPack addData(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public DataOutPack setData(Object data) {
        this.put("data", data);
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return message;
    }
}
