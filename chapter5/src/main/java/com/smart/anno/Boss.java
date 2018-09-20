package com.smart.anno;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class Boss {
	
	private Car car;
	
	public Boss(){
		System.out.println("construct...");
	}

//	@Autowired
//	private void setCar(Car car){
//		System.out.println("execute in setCar");
//		this.car = car;
//	}
	
	@Resource //要求提供一个Bean的名称属性,如果属性为空,则自动采用标注出的变量名或方法名作为Bean的名称
	private void setCar(Car car){
		System.out.println("execute in setCar");
		this.car = car;
	}
	
	@PostConstruct
	private void init1(){
		System.out.println("execute in init1");
	}
	
	@PostConstruct
	private void init2(){
		System.out.println("execute in init1");
	}
	
	@PreDestroy
	private void destory1(){
		System.out.println("execute in destory1");
	}
	
	@PreDestroy
	private void destory2(){
		System.out.println("execute in destory2");
	}

}
