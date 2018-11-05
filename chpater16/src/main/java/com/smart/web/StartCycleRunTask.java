package com.smart.web;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//在web.xml中做了listener配置,当Servlet 容器启动或终止Web 应用时，会触发ServletContextEvent 事件
public class StartCycleRunTask implements ServletContextListener {
	private Timer timer;

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Web应用程序启动关闭...");
//		timer.cancel();
	}

	//实际生产商,也需要web项目停止时候,手工停止这些Timer的异步额外的非deamon线程
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Web应用程序启动...");
		timer = new Timer();
		TimerTask task = new SimpleTimerTask();
		timer.schedule(task, 1000L, 5000L);
	}
}
class SimpleTimerTask extends TimerTask {
	private int count;
	public void run() {
		System.out.println((++count)+"execute task..."+(new Date()));
	}
}
