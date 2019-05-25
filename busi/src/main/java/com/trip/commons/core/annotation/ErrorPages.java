package com.trip.commons.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 多个错误配置页面
 * @author fqh
 * @create 2016-12-10 1:13
 */
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ErrorPages {

    ErrorPage[] value();

}
