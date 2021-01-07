package com.example.programlock.annotation.config;


import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)  //jvm运行时有效
@Target(value = {ElementType.METHOD})  //作用于方法上
@Documented  //该类可以被javaDoc或类似的工具文档化
public @interface CherryAnnotation {
    String name();
    int age() default 18;
    int[] score();

}
