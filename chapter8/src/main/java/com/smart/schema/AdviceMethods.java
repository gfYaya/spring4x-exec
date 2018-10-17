package com.smart.schema;

import org.aspectj.lang.ProceedingJoinPoint;


public class AdviceMethods {

	//增强方法,在beans.xml中被引用<aop:aspect .../> 所被引用
	public void preGreeting(String name) {
		System.out.println("--how are you!--");
		System.out.println(name);
	}

    //后置增强对应方法 入参是retVal必须要和配置文件beans.xml的配置中的returning要一致
	public void afterReturning(int retVal){
	   System.out.println("----afterReturning()----");
	   System.out.println("returnValue:"+retVal);
	   System.out.println("----afterReturning()----");
	}

    //环绕增强对应方法 pjp可以访问到环绕增强的连接点
	public void aroundMethod(ProceedingJoinPoint pjp){
	   System.out.println("----aroundMethod()----");
	   System.out.println("args[0]:"+pjp.getArgs()[0]);
	   System.out.println("----aroundMethod()----");
	}

    //抛出异常增强  入参iae要和beans.xml配置文件的 throwing="iae"  一致
	public void afterThrowingMethod(IllegalArgumentException iae){
	   System.out.println("----afterThrowingMethod()----");
	   System.out.println("exception msg:"+iae.getMessage());
	   System.out.println("----afterThrowingMethod()----");
	}

    //final增强
	public void afterMethod(){
	   System.out.println("----afterMethod()----");
	}

      //------------绑定连接点参数----------//
	public void bindParams(int num,String name){
	   System.out.println("----bindParams()----");
	   System.out.println("name:"+name);
	   System.out.println("num:"+num);
	   System.out.println("----bindParams()----");
	}
}
