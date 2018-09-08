package com.smart.chapter3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication //@SpringBootApplication 被 @Configuration、@EnableAutoConfiguration、@ComponentScan 注解所修饰,换言之 Springboot 提供了统一的注解来替代以上三个注解
@RestController
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Chapter3Application {

    @RequestMapping("/")
    public String index(){
        return "欢迎光临小春论坛";
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter3Application.class, args);
    }
}
