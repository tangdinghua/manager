package com.trip.base.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yk on 2016/11/23.
 */
public class ActionLogInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        String module = actionLog.module();
//        String userid = jwtUtil.getValue("userid");
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        System.out.println(object);
//        if(object instanceof Map){
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            String url = request.getRequestURI();
//            if(url.indexOf("save")!=-1||url.indexOf("delete")!=-1){
//                Map<String,Object> paramMap = request.getParameterMap();
//                com.talkweb.wy.entity.ActionLog al = new com.talkweb.wy.entity.ActionLog();
//                Map<String,Object> map = (Map)object;
//                Map<String,Object> headMap = (Map<String,Object>)map.get("h");
//                String code = (String)headMap.get("c");
//                al.setCreateuser(userid);
//                al.setUrl(url);
//                al.setModule(module);
//                al.setParams(JsonUtil.getGson().toJson(paramMap));
//                if("0".equals(code)){
//                    al.setStatus("0");
//                }else{
//                    al.setStatus("1");
//                }
//                actionLogService.insert(al);
//            }
    //    }
    }
}
