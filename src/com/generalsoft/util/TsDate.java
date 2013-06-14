package com.generalsoft.util;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: wy
 * Date: 13-4-14
 * Time: 下午9:59
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TsDate {
    String writeFormat() default "yyyyMMdd"; //yyyyMMddHHmmss
    String readFormat() default "yyyy-MM-dd"; //yyyy-MM-dd HH:mm:ss
}
