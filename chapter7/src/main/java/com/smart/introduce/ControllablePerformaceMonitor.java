package com.smart.introduce;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

// DelegatingIntroductionInterceptor是IntroductionInterceptor的实现类,IntroductionInterceptor与ThrowsAdvice是一个空接口
public class ControllablePerformaceMonitor extends DelegatingIntroductionInterceptor implements Monitorable, Testable {
	//保存性能监视开关状态.之所以使用ThreadLocal,是因为这个控制状态是代理类变成了非线程安全的实例,为了解决单实例线程安全问题,
	//通过ThreadLocal让每个线程单独使用一个状态.
	private ThreadLocal<Boolean> MonitorStatusMap = new ThreadLocal<Boolean>();

	public void setMonitorActive(boolean active) {
		MonitorStatusMap.set(active);
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable { //拦截方法,重写父类方法,用于拦截目标类方法的调用
		Object obj = null;
		//对于支持性能监视可控代理,通过判断其状态决定是否开启性能监控功能
		if (MonitorStatusMap.get() != null && MonitorStatusMap.get()) {
			PerformanceMonitor.begin(mi.getClass().getName() + "." + mi.getMethod().getName());
			obj = super.invoke(mi);
			PerformanceMonitor.end();
		} else {
			obj = super.invoke(mi);
		}
		return obj;
	}

	public void test() {
		// TODO Auto-generated method stub
		System.out.println("dd");
	}

}
