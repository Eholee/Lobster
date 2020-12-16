package com.eholee.lobster.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author:Jeffer
 * Time:2020/12/16  11:13 PM  Wednesday
 * Description: 子模块Application实例注解
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD})
public @interface AppInstance {
}
