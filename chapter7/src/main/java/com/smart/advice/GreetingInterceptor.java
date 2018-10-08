package com.smart.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GreetingInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object[] args = invocation.getArguments();//目标方法入参
		String clientName = (String)args[0];
		System.out.println("How are you！Mr."+clientName+".");//目标方法之前执行

		Object obj = invocation.proceed();//通过反射机制调用目标方法
		
		System.out.println("Please enjoy yourself!");//目标方法之后执行
		
		return obj;
	}
}
