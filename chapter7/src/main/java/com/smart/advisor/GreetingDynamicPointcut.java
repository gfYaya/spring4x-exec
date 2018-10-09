package com.smart.advisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

// DynamicMethodMatcherPointcut是一个抽象类,他将isRuntime()标识为final且返回true,这样其子类一定是一个动态切点
public class GreetingDynamicPointcut extends DynamicMethodMatcherPointcut {
	private static List<String> specialClientList = new ArrayList<String>();
	static {
		specialClientList.add("John");
		specialClientList.add("Tom");
	}

	public ClassFilter getClassFilter() { //对类进行静态切点检查
		return new ClassFilter() {
			public boolean matches(Class clazz) {
				System.out.println("调用getClassFilter()对"+clazz.getName()+"做静态检查.");
				return Waiter.class.isAssignableFrom(clazz);
			}
		};
	}

	public boolean matches(Method method, Class clazz) { //对方法进行静态切点检查
		System.out.println("调用matches(method,clazz)对"+clazz.getName()+"."+method.getName()+"做静态检查.");
		return "greetTo".equals(method.getName());
	}

	public boolean matches(Method method, Class clazz, Object[] args) { //对方法进行动态切点检查
		System.out.println("调用matches(method,clazz)对"+clazz.getName()+"."+method.getName()+"做动态检查.");
		String clientName = (String) args[0];
		return specialClientList.contains(clientName);
	}

}
