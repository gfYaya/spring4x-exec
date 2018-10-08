package com.smart.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object obj) throws Throwable {//在目标类方法调用前执行
		/*String clientName = null;
		if(args != null && args.length > 0){
			clientName = (String)args[0];
		}*/
		String clientName = args != null && args.length > 0 ? (String)args[0] : null;
		System.out.println("How are you！Mr."+clientName+".");
	}

}
