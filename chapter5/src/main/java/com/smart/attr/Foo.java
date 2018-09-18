package com.smart.attr;

public class Foo {
	//非法的属性变量名,Java语言本身不会报错,因为他讲iDCode看成普通的变量
	private String iDCode;
	//该Setter方法对应IDCode属性而不是iDcode(in spring)
	public void setIDCode(String iDcode) {
		this.iDCode = iDcode;
	}
}
