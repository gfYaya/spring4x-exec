package com.smart.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class MailSendEvent extends ApplicationContextEvent {
	private String to;

	/*
	 * @param to  发送目的地
	 */
	public MailSendEvent(ApplicationContext source, String to) {
		super(source);
		this.to = to;
	}

	public String getTo() {
		return this.to;
	}

}
