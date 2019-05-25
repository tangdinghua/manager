package com.trip.base.aspect;

import com.trip.base.util.JwtUtil;
import com.trip.base.util.ResultUtil;
import com.trip.base.entity.Dict;
import com.trip.base.service.DictService;
import com.trip.base.service.OperationLogService;
import com.trip.base.thread.ProcessLogThread;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yk on 2016/12/8.
 */
@Aspect
@Component
public class RolesAspect {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private DictService dictService;

    @Autowired
    private OperationLogService operationLogService;

    private boolean checkAnon(String url) {
        List<Dict> list = dictService.findByCode("anon");
        for (Dict dict : list) {
            String value = dict.getValue();
            if (value.equals(url)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkNoauth(String url) {
        List<Dict> list = dictService.findByCode("noauth");
        for (Dict dict : list) {
            String value = dict.getValue();
            if (value!=null&&value.equals(url)) {
                return true;
            }
        }
        return false;
    }


    @Around("execution(* com.talkweb.*.action..*.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = new Date().getTime();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURI();
        url = url.substring(url.indexOf(request.getContextPath()) + request.getContextPath().length()).replaceAll("/", "");
        boolean isAnon = checkAnon(url);
        boolean isNoauth = checkNoauth(url);
        String token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }
        isAnon = true;
        isNoauth = true;
        if (!isAnon && !isNoauth) {
            Map<String, Object> resultMap = ResultUtil.getResultMap("1000", "请重新登录");
            if (token == null) {
                return resultMap;
            }
            Claims claims = jwtUtil.parseJWT(token);
            Date expirDate = claims.getExpiration();
            Date now = new Date();
            if (now.getTime() >= expirDate.getTime()) {
                return resultMap;
            }
            String rolecode = jwtUtil.getValue("rolecode");
            if (rolecode != null && rolecode.indexOf("admin") == -1) {//管理员有所有的权限
                String resources = jwtUtil.getValue("resources");
                if (resources.indexOf(url) == -1) {
                    resultMap = ResultUtil.getResultMap("9999", "没有权限");
                    return resultMap;
                }
            }
        }
        Class cla = joinPoint.getTarget().getClass();
        ActionLog actionLog = (ActionLog) cla.getAnnotation(ActionLog.class);
        Object object = joinPoint.proceed();
        if (actionLog != null) {
            String module = actionLog.module();
            if (!StringUtils.isEmpty(token)&&url.indexOf("query")==-1&&!isAnon&&!isNoauth) {
                String userid = jwtUtil.getValue("userid");
                String urltemp = request.getRequestURI();
                Map<String, String[]> paramMap = request.getParameterMap();
                String ip = getIpAddr(request);
                ProcessLogThread processLogThread = new ProcessLogThread(module,urltemp,ip,userid,paramMap,object);
                processLogThread.run();
            }

        }
        return object;
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        System.out.println("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            System.out.println("Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            System.out.println("WL-Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            System.out.println("HTTP_CLIENT_IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            System.out.println("X-Real-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            System.out.println("getRemoteAddr ip: " + ip);
        }
        System.out.println("获取客户端ip: " + ip);
        return ip;
    }
}
