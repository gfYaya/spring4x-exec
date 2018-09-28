package com.smart.i18n;

import java.util.GregorianCalendar;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

public class I18nGreeting {
   
	private static void rsrBdlMessageResource(){
		String[] configs = {"com/smart/i18n/beans.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);

		//获取MessageSource的Bean
		//MessageSource ms = (MessageSource)ctx.getBean("myResource1");
		//ResourceBundleMessageSource 是beans.xml 中bean实例myResource1对应的class属性
		MessageSource ms = (ResourceBundleMessageSource)ctx.getBean("myResource1");
		Object[] params = {"John", new GregorianCalendar().getTime()};

		//获取格式化的国际化信息
		String str1 = ms.getMessage("greeting.common",params,Locale.US);
		String str2 = ms.getMessage("greeting.morning",params,Locale.CHINA);
		String str3 = ms.getMessage("greeting.afternoon",params,Locale.CHINA);
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		
	}

	private static void rrsrBdlMessageResource() throws Exception{
		String[] configs = {"com/smart/i18n/beans.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);
		
		MessageSource ms = (MessageSource)ctx.getBean("myResource2");
		Object[] params = {"John", new GregorianCalendar().getTime()};
		
		for (int i = 0; i < 2; i++) {
			String str1 = ms.getMessage("greeting.common",params,Locale.US);		
			System.out.println(str1);
			Thread.currentThread().sleep(20000);//模拟程序应用,在此期间,修改资源文件
		}	
	}
	
	private static void ctxMessageResource() throws Exception{
		String[] configs = {"com/smart/i18n/beans.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);
		Object[] params = {"John", new GregorianCalendar().getTime()};

		/* 并未如上述的方法,去直接获取某个Bean (getBean() ) 或者直接拿到MessageResource,因为beans.xml中已经注册一个名为
		      messageSource的bean,如果未注册,则报出 org.springframework.context.NoSuchMessageException 的异常.
		reason: 在AbstractApplicationContext中的refresh()方法中的一步操作,initMessageSource()就是初始化容器中的国际化信息资源,它根据反射
           机制从BeanDefinitionRegistry中找出名为messageSource且类型为org.springframework.context.MessageResource的Bean.将这个Bean定义
            的信息资源加载为容器级的国际化信息资源.
		*/
		String str1 = ctx.getMessage("greeting.common",params,Locale.US);
		String str2 = ctx.getMessage("greeting.morning",params,Locale.CHINA);	
		System.out.println(str1);
		System.out.println(str2);	
	}

	public static void main(String[] args) throws Exception{
//		rsrBdlMessageResource();
//		rrsrBdlMessageResource();
		ctxMessageResource();
	}


}
