package com.smart.aspectj.example;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect //通过注解将PreGreetingAspect 标识位一个切面
public class PreGreetingAspect {

    @Before("execution(* greetTo(..))") //定义切点和增强类型
    public void beforeGreeting(){ //增强横切逻辑
        System.out.println("How are you");
    }


}
