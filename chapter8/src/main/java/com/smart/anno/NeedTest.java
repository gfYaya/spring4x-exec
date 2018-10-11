package com.smart.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //申明注解保留期限
@Target(ElementType.METHOD) //声明可以使用该注解的类型
public @interface NeedTest { //定义注解
    boolean value() default true; //声明注解成员,无入参,无抛出异常的方式声明,可以不注定默认类型,但成员类型有限
}
