package com.smart.ditype;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import  static  org.testng.Assert.*;

public class DiTypeTest {
    private ApplicationContext ctx = null;

    private static String[] CONFIG_FILES = { "com/smart/ditype/beans.xml"};

    @BeforeClass
    public void setUp() throws Exception{
        ctx = new ClassPathXmlApplicationContext(CONFIG_FILES);
    }

    @Test
    public void testCar(){
        Car car = (Car)ctx.getBean("car");
        assertNotNull(car);
        System.out.println(car);
    }

    @Test
    public void testCar1(){
        Car car1 = (Car)ctx.getBean("car1");
        assertNotNull(car1);
        System.out.println(car1);
    }
    @Test
	public void testCar2(){
		Car car2 = (Car)ctx.getBean("car2");
		assertNotNull(car2);
		System.out.println(car2);
	}

    @Test
    public void testCar3(){
        Car car3 = (Car)ctx.getBean("car3");
        assertNotNull(car3);
        System.out.println("car3:"+car3);
    }

    @Test
    public void testCar4(){
        Car car4 = (Car)ctx.getBean("car4");
        assertNotNull(car4);
        System.out.println("car4:"+car4);
    }

    @Test
    public void testCar5(){
        Car car5 = (Car)ctx.getBean("car5");
        assertNotNull(car5);
        System.out.println("car5:"+car5);
    }

    @Test
    public void testCar6(){
        Car car6 = (Car)ctx.getBean("car6");
        assertNotNull(car6);
        System.out.println("car6:"+car6);
    }


    @Test
    public void testBoss(){
        Boss boss = (Boss)ctx.getBean("boss");
        assertNotNull(boss);
        System.out.println("boss:"+boss);
    }

}
