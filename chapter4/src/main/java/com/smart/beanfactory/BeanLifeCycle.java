package com.smart.beanfactory;

import com.smart.Car;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author zy
 * @DATE 2018/9/15  18:18
 */

public class BeanLifeCycle {

    private static void LifeCycleInBeanFactory(){
        //下面两句装载配置文件并启动容器
        Resource res = new ClassPathResource("com/smart/beanfactory/beans.xml");
        BeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((DefaultListableBeanFactory)bf); //此时加入的容器,并在一下步顺带启动
        reader.loadBeanDefinitions(res);

        ///PS:后处理器的实际调用顺序和注册顺序无关,在具有多个后处理器的情况下,必须通过实现spring.framework.core.Ordered接口来调定顺序
        //向容器中注册MyBeanPostProcessor后处理器
        ((ConfigurableBeanFactory)bf).addBeanPostProcessor(new MyBeanPostProcessor());
        //向容器中注册MyInstantiationAwareBeanPostProcessor后处理器
        ((ConfigurableBeanFactory)bf).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        //第一次向容器中获取car,将触发容器实例化该Bean,并放入缓存池中,这将引发bean生命周期方法的调用
        Car car1 = (Car)bf.getBean("car");
        car1.introduce();
        car1.setColor("红色");

        //第二次从容器中获取car,直接从缓存池中取,不会引发生命周期相关方法
        Car car2 = (Car)bf.getBean("car");

        //查看一下car1和car2是否指向同一引用
        System.out.println("car1 == car2:"+(car1 == car2));

        //关闭容器
        ((DefaultListableBeanFactory)bf).destroySingletons();
    }

    public static void main(String[] args) {
        LifeCycleInBeanFactory();
    }

}
