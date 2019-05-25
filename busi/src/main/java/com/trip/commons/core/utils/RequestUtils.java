package com.trip.commons.core.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * 请求工具类
 *
 * @author fqh
 * @create 2016-12-10 3:09
 */
public class RequestUtils {

    /**
     * 把普通请求转化为Http请求
     * @param request
     * @return
     */
    public static HttpServletRequest wrap(ServletRequest request) {
        if(request instanceof HttpServletRequest) {
            return (HttpServletRequest) request;
        }
        return null;
    }

    /**
     * 判断请求是否为Ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return request.getHeader("Accept").indexOf("application/json") > -1 || request.getHeader("X-Requested-With") != null;
    }

}
