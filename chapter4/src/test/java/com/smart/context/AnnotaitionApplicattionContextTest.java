package com.smart.context;

import com.smart.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

import java.beans.Beans;

import static junit.framework.Assert.assertNotNull;

/**
 * @author zy
 * @DATE 2018/9/15  10:30
 */
public class AnnotaitionApplicattionContextTest {

    @Test
    public void getBean(){
        //通过一个带@Configuration的POJO装载Bean配置,只加载被@Configuration修饰的Bean，xml无效
        //// TODO: 2018/9/15  依然无效
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
        Car car = ctx.getBean("car",Car.class);
        assertNotNull(car);

    }
}
