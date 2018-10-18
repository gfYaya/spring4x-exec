package com.smart.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyDataSource {

    @Value("#{properties['driverClassName']}")
	private String driverClassName;
    
    @Value("${url}")
	private String url;
    
    @Value("${username}") //等价 @Value("#properties['username']")
	private String userName;
    
    @Value("#{properties['password']}")
	private String password;

}
