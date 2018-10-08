package com.smart.proxy;

public class ForumServiceImpl implements ForumService {

	public void removeTopic(int topicId) {
		//开始对该方法进行性能监测
		//PerformanceMonitor.begin("com.smart.proxy.ForumServiceImpl.removeTopic");

		System.out.println("模拟删除Topic记录:"+topicId);
		try {
			Thread.currentThread().sleep(20);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		//结束对该方法的性能监测
		//PerformanceMonitor.end();
	}

	public void removeForum(int forumId) {
		//PerformanceMonitor.begin("com.smart.proxy.ForumServiceImpl.removeForum");

		System.out.println("模拟删除Forum记录:"+forumId);
		try {
			Thread.currentThread().sleep(40);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		//PerformanceMonitor.end();
	}

	//非接口需要实现的方法,是否被动态代理实现了性能检测方法
	public void sayHello(){
        System.out.println("hello");
    }

}
