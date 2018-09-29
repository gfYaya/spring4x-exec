package com.smart.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformaceHandler implements InvocationHandler {
    private Object target;

	public PerformaceHandler(Object target){ //target为目标业务类
		this.target = target;
	}

	/*  Processes a method invocation on a proxy instance and returns the result.
	 *  @param  proxy the proxy instance that the method was invoked on
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		PerformanceMonitor.begin(target.getClass().getName()+"."+ method.getName());
		Object obj = method.invoke(target, args); //通过反射类调用目标业务类方法
		PerformanceMonitor.end();
		return obj;
	}

}
