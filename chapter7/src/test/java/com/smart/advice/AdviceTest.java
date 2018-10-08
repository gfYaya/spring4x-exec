package com.smart.advice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.testng.Assert.*;
import org.testng.annotations.*;

public class AdviceTest {

	@Test
	public void advice() {
		String configPath = "com/smart/advice/beans.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		Waiter waiter = (Waiter)ctx.getBean("waiter1");
		waiter.greetTo("John");
		System.out.println("-----------------");
		Waiter waiter2 = (Waiter)ctx.getBean("waiter2");
		waiter2.greetTo("Yaya");
		System.out.println("-----------------");
		//环绕,很像自己手写的代理增强 com.smart.proxy包下
		Waiter waiter3 = (Waiter)ctx.getBean("waiter3");
		waiter3.greetTo("Yaya");
	}

}
