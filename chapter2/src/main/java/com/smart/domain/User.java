package com.smart.domain;

import java.io.Serializable;
import java.util.Date;

//领域对象(实体类)一定要实现Serializable接口,以便可以序列化
/* https://spring.io/guides/gs/relational-data-access/
 * 目前从 spring-jdbc 官网看到的文档和例子并没有一定要实现序列化,应该是作者自己提供的序列化.
 */
public class User implements Serializable{
	private int userId;

	private String userName;

	private String password;

	private int credits;

	private String lastIp;

	private Date lastVisit;

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
}
