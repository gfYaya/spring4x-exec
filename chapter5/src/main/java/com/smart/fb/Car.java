package com.smart.fb;

public class Car {
	private int maxSpeed;
	public String brand;
	private double price;
	private Boss boss;//临时增加的变量,用于和Boss做依赖,负责父子容器检测

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

	public String toString() {
		return "brand:" + brand + "/maxSpeed:" + maxSpeed + "/price:" + price;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}
}
