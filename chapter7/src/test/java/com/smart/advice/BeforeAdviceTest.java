package com.smart.advice;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import static org.testng.Assert.*;
import org.testng.annotations.*;

public class BeforeAdviceTest {

    @Test
	public void before() {
        Waiter target = new NaiveWaiter();
        BeforeAdvice  advice = new GreetingBeforeAdvice();
        //Spring提供的代理工厂
        ProxyFactory pf = new ProxyFactory();
        //BeforeAdviseTest 使用的是CgLib动态代理技术,当我们指定接口代理的时候,将使用JDK动态代理技术
        //pf.setInterfaces(target.getClass().getInterfaces());
        //启用代理优化,ProxyFactory将使用Cglib2AopProyx代理
        pf.setOptimize(true);
        //设置代理目标
        pf.setTarget(target);
        //为代理目标添加增强
        pf.addAdvice(advice);
        //成为代理实例
        //Waiter proxy = (Waiter)pf.getProxy();
        NaiveWaiter proxy = (NaiveWaiter)pf.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("Tom");
        proxy.say(); //error: 执行报错,非接口Waiter的方法 报错,即便是去掉 pf.setInterfaces(target.getClass().getInterfaces()); 这个接口代理
	}
}
