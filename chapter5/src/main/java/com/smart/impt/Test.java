package com.smart.impt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.smart.fb.Car;

public class Test {

	  public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("com/smart/impt/beans2.xml");
		 Car car = ctx.getBean("car1",Car.class);
		 System.out.println(car.getMaxSpeed());

		 //测试import的xml容器是否存在父子容器?
		 ApplicationContext ctx1 = new ClassPathXmlApplicationContext("com/smart/impt/beans1.xml");
		 Car car1 = ctx1.getBean("car1",Car.class);
		  System.out.println(car.getBoss());
	}

}
