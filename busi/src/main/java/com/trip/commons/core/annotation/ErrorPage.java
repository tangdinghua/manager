package com.trip.commons.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 配置错误跳转页面
 * @author fqh
 * @create 2016-12-10 1:10
 */
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ErrorPage {

    /**
     * 错误编码
     * @return
     */
    public String code() default "8000";

    /**
     * 错误页面
     * @return
     */
    public String page();

}
