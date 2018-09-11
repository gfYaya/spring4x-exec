package com.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

//@SpringBootApplication //@SpringBootApplication 被 @Configuration、@EnableAutoConfiguration、@ComponentScan 注解所修饰,换言之 Springboot 提供了统一的注解来替代以上三个注解
//@RestController
@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableTransactionManagement //启用注解事务事务管理,对用户不透明,Enables Spring's annotation-driven transaction management capability, similar to the support found in Spring's <tx:*> XML namespace.
public class Chapter3Application extends SpringBootServletInitializer {//Servlet初始化器

    //@RequestMapping("/")
    /*public String index(){
        return "欢迎光临小春论坛";
    }
    */

    @Override //重写SpringBootServletInitializer 的configue方法
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Chapter3Application.class);
    }

    //todo 需要使用maven的spring boot plugins 启动才能正常访问 不知道为什么...
    //https://www.oschina.net/question/2930515_2199881
    //Added by 海涛:主要是 pom 里面的 tomcat 那个包的问题  => pom.xml , <package> war </> ,打成war包在tomcat下确实好使
    public static void main(String[] args) {
        SpringApplication.run(Chapter3Application.class, args);
    }

    @Bean //自定义事务管理器
    public PlatformTransactionManager txManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
