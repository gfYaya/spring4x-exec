package com.smart.attr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class ParentContainerBeanTest {

    @Test
    public void parent(){
        //父容器
        ClassPathXmlApplicationContext pFactory = new ClassPathXmlApplicationContext("com/smart/attr/beans1.xml");
        //指定pFactory为该容器的父容器
        ApplicationContext factory = new ClassPathXmlApplicationContext(new String[]{"com/smart/attr/beans2.xml"},pFactory);
        Boss boss = (Boss)factory.getBean("boss");
        assertNotNull(boss);
        System.out.println(boss.getCar());
    }

}
