package com.smart.context;

import com.smart.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author zy
 * @DATE 2018/9/15  12:15
 */
public class GroovyApplicationContextTest {

    @Test
    public void getBean(){
        ApplicationContext ctx = new GenericGroovyApplicationContext("classpath:com/smart/context/groovy-beans.groovy");
        Car car = (Car)ctx.getBean("car");
        assertNotNull(car);
        assertEquals(car.getColor(),"red");
    }
}