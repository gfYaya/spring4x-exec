package com.smart.autoproxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class AopAwareTest {
	@Test
	public void autoProxy() {
		String configPath = "com/smart/autoproxy/beans-aware.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		Waiter waiter = (Waiter) ctx.getBean("waiter");
		waiter.serveTo("John"); //在同一个类的内部方法之间调用的方法greetTo()无法被增强,需要借助其他代理类调用内部方法(e.g:BeanSelfProxyAware)
		//waiter.greetTo("John");
	}

}
