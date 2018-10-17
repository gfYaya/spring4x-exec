package com.smart.aspectj.advanced;

import com.smart.NaiveWaiter;
import com.smart.SmartSeller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.smart.Waiter;
import org.testng.annotations.*;

public class AdvancedTest {

    @Test
	public void advance() {
		String configPath = "com/smart/aspectj/advanced/beans.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		//Waiter naiveWaiter = (Waiter) ctx.getBean("naiveWaiter");
		NaiveWaiter naiveWaiter = (NaiveWaiter) ctx.getBean("naiveWaiter");
		Waiter naughtyWaiter = (Waiter) ctx.getBean("naughtyWaiter");
		naiveWaiter.greetTo("John");
		naiveWaiter.serveTo("John");
		naughtyWaiter.greetTo("Tom");
		naughtyWaiter.serveTo("Tom");
		
        //--通过joinPoint接口访问连接点上下文信息
		naiveWaiter.greetTo("John");
		
		//--绑定连接点参数
		/* 必须在beans.xml中开启cglib才行 ,即<aop:aspectj-autoproxy proxy-target-class="true"/>
		 jdk代理只能代理接口 所以只能转型为Waiter ,jdk代理只能增强接口实现的方法 
		所以增强子类自身额外实现的方法不行因为对象上转型用不了,但是cglib可以
		 */
		//((NaiveWaiter)naiveWaiter).smile("John",2);
		naiveWaiter.smile("John",2);

		System.out.println("------------------------------");
		//--绑定代理对象
		naiveWaiter.greetTo("John");
		
		//--绑定类注解
		((NaiveWaiter)naiveWaiter).greetTo("John");

		//绑定返回值
		SmartSeller seller = (SmartSeller) ctx.getBean("seller");
		seller.sell("Beer","John");
		
		//绑定异常
		SmartSeller seller1 = (SmartSeller) ctx.getBean("seller");
		//seller1.checkBill(2);
		seller1.checkBill(1);
	}
}
