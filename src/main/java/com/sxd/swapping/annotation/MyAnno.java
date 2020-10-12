package com.sxd.swapping.annotation;

import com.sxd.swapping.enums.MyEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MyAnno {

    String myName();

    MyEnum[] myEnums() default {};
}
