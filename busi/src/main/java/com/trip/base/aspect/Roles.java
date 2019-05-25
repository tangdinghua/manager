package com.trip.base.aspect;

/**
 * Created by yk on 2016/11/23.
 */

import java.lang.annotation.*;

@Inherited
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Roles {
}
