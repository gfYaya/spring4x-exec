package com.smart.ditype;

public class Car {
	public String brand;
	private String corp;	
	private double price;
	private int maxSpeed;

	/*
	 todo:warning 依赖注入
	 属性注入必须要有无参构造，如果没有手动声明其他构造，默认会有无参构造
     构造器注入必须有对应的有参构造，也可以有别的构造
	 */

	public Car() {}	
	public Car(String brand, double price) {
		this.brand = brand;
		this.price = price;
	}	

	public Car(String brand, String corp, double price) {
		this.brand = brand;
		this.corp = corp;
		this.price = price;
	}
	public Car(String brand, String corp, int maxSpeed) {
		this.brand = brand;
		this.corp = corp;
		this.maxSpeed = maxSpeed;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString(){
		return "brand:"+brand+"/maxSpeed:"+maxSpeed+"/price:"+price;
	}

}

