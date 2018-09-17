package com.smart.beanfactory;

import com.smart.Car;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.testng.annotations.Test;

import java.io.IOException;

public class BeanFactoryTest {

    @Test
    public void getBean() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("classpath:com/smart/beanfactory/beans.xml");
        System.out.println(res.getURL());
        //被废弃,不建议使用
        //BeanFactory bf = new XmlBeanFactory(res);

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //通过resource装载Spring配置信息并启动Ioc容器
        //warn:通过BeanFactory启动Ioc并不会初始化配置文件中定义的Bean,初始化反生在第一次调用,对于单例而言会缓存该Bean.
        // 而ApplicationContext则在初始化应用上下文时就实例化所有单实例的Bean.
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(res);

        System.out.println("init factory");

        //从Ioc容器中获取bean
        Car car = factory.getBean("car", Car.class);
        System.out.println("Car bean is ready for use");
        car.introduce();
    }

}
