package com.trip.base.aspect;

import com.trip.base.util.ResultUtil;
import com.trip.base.constants.Constants;
import com.trip.base.exception.BusinessException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by yk on 2016/11/24.
 */
@Aspect
@Component
public class ExceptionAspect {

    @Around("execution(* com.talkweb.*.action..*.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            if (throwable instanceof BusinessException) {
                if (throwable.getMessage()!=null&&(throwable.getMessage().indexOf("JWT") != -1 || throwable.getMessage().indexOf("IllegalArgumentException") != -1|| throwable.getMessage().indexOf("ExpiredJwtException") != -1)) {
                    Map<String, Object> resultMap = ResultUtil.getResultMap("1000", "请重新登录");
                    return resultMap;
                }
                BusinessException be = (BusinessException) throwable;
                Map<String, Object> resultMap = ResultUtil.getResultMap(be.getErrorCode(), be.getErrorMsg());
                return resultMap;
            } else if (throwable instanceof ExpiredJwtException || throwable instanceof IllegalArgumentException || throwable instanceof MalformedJwtException) {
                Map<String, Object> resultMap = ResultUtil.getResultMap("1000", "请重新登录");
                return resultMap;
            } else if(throwable.getMessage().indexOf("Duplicate entry")!=-1){
                Map<String, Object> resultMap = ResultUtil.getResultMap("9000", "数据已经存在");
                return resultMap;
            }else{
                Map<String, Object> resultMap = ResultUtil.getResultMap(Constants.SYS_FAIL_CODE, Constants.SYS_FAIL_MSG);
                return resultMap;
            }
        }
        return object;
    }
}
