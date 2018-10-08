package com.smart.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {
	private Enhancer enhancer = new Enhancer();

	public Object getProxy(Class clazz) {
		enhancer.setSuperclass(clazz);//设置需要创建的子类
		enhancer.setCallback(this);
		return enhancer.create();//通过字节码技术动态创建子类实例
	}

	public Object intercept(Object obj, Method method, Object[] args, //拦截父类所有方法的调用
			MethodProxy proxy) throws Throwable {
		PerformanceMonitor.begin(obj.getClass().getName()+"."+method.getName());
		Object result=proxy.invokeSuper(obj, args);//通过代理类调用父类的方法
		PerformanceMonitor.end();
		return result;
	}

}
