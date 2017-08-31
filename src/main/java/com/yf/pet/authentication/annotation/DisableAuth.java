/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.authentication.annotation;

import java.lang.annotation.*;

/**
 *
 * 非鉴权注解，Controller使用此注解，过滤器将不拦截
 *
 * @author infi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface DisableAuth {

}
