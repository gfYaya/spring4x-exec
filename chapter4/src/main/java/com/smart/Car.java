package com.smart;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {//管理Bean生命周期的接口
	private String brand;
	private String color;
	private int maxSpeed;
	private String name;
	private BeanFactory beanFactory;
	private String beanName;

	public Car() {
		System.out.println("调用Car()构造函数。");
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		System.out.println("调用setBrand()设置属性。");
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public String toString() {
		return "brand:" + brand + "/color:" + color + "/maxSpeed:"+ maxSpeed;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public void introduce(){
		System.out.println("introduce:"+this.toString());
	}
	

	// BeanFactoryAware接口方法,将BeanFactory容器实例设置到Bean中
	// added by Intopass:bean在工厂中是必然的,但是如果这个bean想要获取工厂的引用呢?比如说有个非单例的bean，你需要多次获取，就可以利用用工厂引用。
	// =>先拿到工厂引用 通过工厂再获取bean.这种用途不多，只是给你个接口好备用而已。
	// example:https://blog.csdn.net/liyuan0323/article/details/69234615
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("调用BeanFactoryAware.setBeanFactory()。");
		this.beanFactory = beanFactory;
	}

	// BeanNameAware接口方法,将配置文件中的该Bean对应的名称设置到该bean中
	public void setBeanName(String beanName) {
		System.out.println("调用BeanNameAware.setBeanName()。");
		this.beanName = beanName;
	}

	// InitializingBean接口方法
	public void afterPropertiesSet() throws Exception {
		System.out.println("调用InitializingBean.afterPropertiesSet()。");
	}

	// DisposableBean接口方法,可以在此编写释放资源和日志记录等操作
	public void destroy() throws Exception {
		System.out.println("调用DisposableBean.destory()。");
	}

	//通过<bean>的init-method属性指定的初始化方法方法
	public void myInit() {
		System.out.println("调用myInit()，将maxSpeed设置为240。");
		this.maxSpeed = 240;
	}

	//通过<Bean>的destroy-method属性指定的销毁方法
	public void myDestory() {
		System.out.println("调用myDestroy()。");
	}

}
