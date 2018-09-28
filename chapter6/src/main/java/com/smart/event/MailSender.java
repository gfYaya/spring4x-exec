package com.smart.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//使MailSender拥有事件发布能力
public class MailSender implements ApplicationContextAware {

	private ApplicationContext ctx ;//事件源

	//ApplicationContextAware接口方法,以便容器启动时注入容器实例
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;

	}

	public void sendMail(String to){
		System.out.println("MailSender:模拟发送邮件...");
		MailSendEvent mse = new MailSendEvent(this.ctx,to);
		//对容器中所有的监听器发送事件
		ctx.publishEvent(mse);
	}
}
