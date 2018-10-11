package com.smart.advisor;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class GreetingComposablePointcut {

   public Pointcut getIntersectionPointcut(){
	   ComposablePointcut cp = new ComposablePointcut(); //创建一个复合切点
	   Pointcut pt1 = new ControlFlowPointcut(WaiterDelegate.class,"service");//创建一个流程切点
	   NameMatchMethodPointcut pt2 = new NameMatchMethodPointcut();//创建一个方法名切点
	   pt2.addMethodName("greetTo");
	   return cp.intersection(pt1).intersection((Pointcut)pt2); //两个切点进行交集操作
   }
}
