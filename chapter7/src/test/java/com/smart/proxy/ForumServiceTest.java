package com.smart.proxy;
import java.lang.reflect.Proxy;
import static org.testng.Assert.*;
import org.testng.annotations.*;

public class ForumServiceTest {


	@Test
	public void proxy() {
		// 业务类正常编码的测试
//		 ForumService forumService = new ForumServiceImpl();
//		 forumService.removeForum(10);
//		 forumService.removeTopic(1012);

		// 使用JDK动态代理
		//ForumService target = new ForumServiceImpl(); //希望被代理的目标业务类
		ForumServiceImpl target = new ForumServiceImpl();
		PerformaceHandler handler = new PerformaceHandler(
				target  //将目标业务类和横切编织到一起
		                                                  );
		//根据编织了目标业务类逻辑和性能监视横切逻辑的InvocationHandler实例创建代理实例
		ForumService proxy = (ForumService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				//JDK动态代理只能对实现了接口的类生成代理，而不能针对类. 这里面的接口指的是 target.getClass().getInterfaces()
				// 因此即便是target不使用对象上转型也可以
				target.getClass().getInterfaces(), handler);
		proxy.removeForum(10);
		proxy.removeTopic(1012);

		//使用CGLib动态代理
//		CglibProxy cglibProxy = new CglibProxy();
//		ForumService forumService2 = (ForumService)cglibProxy.getProxy(ForumServiceImpl.class);
//		forumService2.removeForum(10);
//		forumService2.removeTopic(1023);
		
	}
	 
}
