package com.smart.introduce;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.testng.Assert.*;
import org.testng.annotations.*;
public class IntroduceTest {

    @Test
	public void introduce(){
		String configPath = "com/smart/introduce/beans.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ForumService forumService = (ForumService)ctx.getBean("forumService");

        //默认情况下未开启性能监控
        forumService.removeForum(10);
        forumService.removeTopic(1022);

        Monitorable moniterable = (Monitorable)forumService;
        moniterable.setMonitorActive(true);//开启性能监视

        forumService.removeForum(10);
        forumService.removeTopic(1022);

	}

}
