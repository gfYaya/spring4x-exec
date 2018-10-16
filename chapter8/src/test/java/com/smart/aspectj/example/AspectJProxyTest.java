package com.smart.aspectj.example;

import com.smart.NaiveWaiter;
import com.smart.Waiter;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.testng.annotations.Test;

public class AspectJProxyTest {

    @Test
    public void proxy(){
        Waiter target = new NaiveWaiter();
        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(target); //设置目标对象
        factory.addAspect(PreGreetingAspect.class);//添加切面类
        Waiter proxy = factory.getProxy(); //生成织入切面的代理对象
        proxy.greetTo("Yaya");
        System.out.println("-----------------");
        proxy.serveTo("Yaya");
    }
}
