package com.smart.advice;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

//ThrowsAdvise是一个标签接口,在运行期Spring使用反射机制自行判断,必须采用void afterThrowing(...) 形式定义异常抛出的增强方法
public class TransactionManager implements ThrowsAdvice {

	public void afterThrowing(Method method, Object[] args, Object target,
			Exception ex) throws Throwable {
		System.out.println("-----------");
		System.out.println("method:" + method.getName());
		System.out.println("抛出异常:" + ex.getMessage());
		System.out.println("成功回滚事务。");
	}

}
