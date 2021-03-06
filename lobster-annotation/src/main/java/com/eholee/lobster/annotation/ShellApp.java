package com.eholee.lobster.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author:Jeffer
 * Time:2020/12/16  11:09 PM  Wednesday
 * Description: 壳子（主工程）Application注解
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface ShellApp {
}