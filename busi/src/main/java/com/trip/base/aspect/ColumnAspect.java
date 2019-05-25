package com.trip.base.aspect;

import com.trip.base.util.JwtUtil;
import com.trip.base.util.ResultUtil;
import com.trip.commons.core.bean.IDEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by yk on 2016/12/8.
 */
@Aspect
@Component
public class ColumnAspect {

    @Autowired
    private JwtUtil jwtUtil;

    @Pointcut("execution(* com.talkweb.*.action.*+.*(..))")
    public void aspect() {
    }

    @Around("aspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Class cla = joinPoint.getTarget().getClass();
        ColumnConfig columnConfig = (ColumnConfig) cla.getAnnotation(ColumnConfig.class);
        String methodname = joinPoint.getSignature().getName();
        Object object = joinPoint.proceed();
        Map<String, Object> map = ResultUtil.getResultMap("0", "操作成功");
        Signature sig = joinPoint.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        NoResp noResp = currentMethod.getAnnotation(NoResp.class);
        if(noResp!=null){
            return object;
        }
        boolean isprocess = false;
        if (columnConfig != null) {
            String[] method = columnConfig.method();
            String[] column = columnConfig.column();
            for (int i = 0; i < method.length; i++) {
                String m = method[i];
                if (m.equals(methodname)) {
                    String[] columns = column[i].split(",");
                    if (object instanceof IDEntity) {
                        Map<String, Object> valueMap = new HashMap<String, Object>();
                        for (String s : columns) {
                            PropertyDescriptor pd = new PropertyDescriptor(s, object.getClass());
                            Method getMethod = pd.getReadMethod();
                            Object o = getMethod.invoke(object);
                            valueMap.put(s, o);
                        }
                        map.put("b", valueMap);
                        return map;
                    } else if (object instanceof List) {
                        List list = (List) object;
                        map.put("b", processList(list, columns));
                        return map;
                    } else if (object instanceof Map) {
                        Map tempmap = (Map) object;
                        Map subtempmap = (Map)tempmap.get("b");
                        if(subtempmap!=null&&!subtempmap.isEmpty()){
                            Iterator<String> it = subtempmap.keySet().iterator();
                            while(it.hasNext()){
                                String key = it.next();
                                Object o = subtempmap.get(key);
                                if(o instanceof List){
                                    List list = (List) o;
                                    if(list!=null){
                                        list = processList(list, columns);
                                        subtempmap.put(key,list);
                                    }

                                }
                            }
                        }else{
                            return tempmap;
                        }
                            map.put("b",subtempmap );
                            return map;
                    }
                    isprocess = true;
                }
            }
        }
        System.out.println("object===="+object);
        if (!isprocess && object != null) {
            if(object instanceof Map){
                Map temp = (Map)object;
                if(temp.get("data")!=null){
                    map.put("b",temp.get("data"));
                    map.put("total",temp.get("total"));
                }
                else if(temp.get("b")==null){
                    map.put("b", object);
                }else{
                    return object;
                }
            }else{
                map.put("b",object);
            }
        }
        System.out.println("map===="+object);
        return map;
    }

    private List<Map<String, Object>> processList(List list, String[] columns) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        List<Map<String, Object>> valueList = new ArrayList<Map<String, Object>>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> valueMap = new HashMap<String, Object>();
                Object tempobject = list.get(i);
                for (String s : columns) {
                    PropertyDescriptor pd = new PropertyDescriptor(s, tempobject.getClass());
                    Method getMethod = pd.getReadMethod();
                    Object o = getMethod.invoke(tempobject);
                    valueMap.put(s, o);
                }
                valueList.add(valueMap);

            }
        }
        return valueList;
    }
}
