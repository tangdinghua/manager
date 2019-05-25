package com.trip.base.aspect;

import com.trip.base.util.JwtUtil;
import com.trip.base.util.ResultUtil;
import com.trip.base.constants.Constants;
import com.trip.base.service.UserService;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by yk on 2016/12/8.
 */
@Aspect
@Component
public class LoginAspect {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Around("@annotation(loginCheck)")
    public Object doAround(ProceedingJoinPoint joinPoint, LoginCheck loginCheck) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        Map<String, Object> resultMap = ResultUtil.getResultMap("1000", "请重新登录");
        if(token==null){
            return resultMap;
        }
        Claims claims = jwtUtil.parseJWT(token);
        Date expirDate = claims.getExpiration();
        Date now = new Date();
        if(now.getTime()>=expirDate.getTime()){
            return resultMap;
        }
        String sysid = jwtUtil.getValue("sysid");
        String tokenStr = jwtUtil.getValue("token");
        if(!Constants.SYS_ID.equals(sysid)){
            return ResultUtil.getResultMap("1000","请重新登录");
        }
        Map<String,String> map = userService.queryToken(tokenStr,Long.parseLong(jwtUtil.getValue("userid")));
        if(map.isEmpty()){
            return ResultUtil.getResultMap("1000","请重新登录");
        }
        return joinPoint.proceed();
    }
}
