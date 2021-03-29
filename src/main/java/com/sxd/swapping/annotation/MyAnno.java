package com.sxd.swapping.annotation;

import com.sxd.swapping.enums.MyEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 1.运行时注解   https://blog.csdn.net/github_35180164/article/details/52118286
 * 2.可用在方法的注解 https://blog.csdn.net/qq_37126357/article/details/101196335
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MyAnno {

    String myName();

    MyEnum[] myEnums() default {};
}
