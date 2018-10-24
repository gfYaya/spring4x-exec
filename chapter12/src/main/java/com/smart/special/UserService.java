/**
 * Sample软件公司, 版权所有 违者必究
 * Copyright 2010 
 * 2010-2-19
 */
package com.smart.special;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author 陈雄华
 * @version 1.0
 */

//对于cglib ,private static final方法无法被动态代理增强,即无法开启事务增强
@Service("userService")
public class UserService implements UserServiceInterface{
    //private 因为访问权限限制,无法被子类覆盖
    private void method1() {
        System.out.println("in method1");
    }

    //final方法无法被子类覆盖
    public final void method2() {
        System.out.println("in method2");
    }

    //static是类级别方法,无法被子类覆盖
    public static void method3() {
        System.out.println("in method3");
    }

    public void method4() {
        System.out.println("in method4");
    }

    //final方法
    public final void method5() {
        System.out.println("in method5");
    }

    protected void method6(){
        System.out.println("in method6");
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/smart/special/applicationContext.xml");
        UserService service = (UserService) ctx.getBean("userService");

        System.out.println("before method1");
        service.method1();
        System.out.println("after method1");

        System.out.println("before method2");
        service.method2();
        System.out.println("after method2");

        System.out.println("before method3");
        service.method3();
        System.out.println("after method3");

        System.out.println("before method4");
        service.method4();
        System.out.println("after method4");

		System.out.println("before method5");
		service.method5();
		System.out.println("after method5");

		System.out.println("before method6");
		service.method6();
		System.out.println("after method6");

        //基于接口的动态代理
//        UserServiceInterface service = (UserServiceInterface) ctx.getBean("userService");
//        System.out.println("before method4");
//        service.method4();
//        System.out.println("after method4");
//
//        System.out.println("before method5");
//        service.method5();
//        System.out.println("after method5");
    }
}
