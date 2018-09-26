package com.smart.placeholder;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component //使用@Value必须要有@Component 这个annotation
public class MyDataSource {

    @Value("${driverClassName}") //应用配置中的值
	private String driverClassName;
    
    @Value("${url}")
	private String url;
    
    @Value("${userName}")
	private String userName;
    
    @Value("${password}")
	private String password;
    
    public String toString(){
    	 return ToStringBuilder.reflectionToString(this);
    }	
}
