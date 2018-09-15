package com.smart.beanfactory;

import com.smart.Car;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author zy
 * @DATE 2018/9/15  18:07
 */
public class MyBeanPostProcessor implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("car".equals(beanName)){
            Car car = (Car)bean;
            if(car.getColor() == null){
                System.out.println("调用BeanPostProcessor.postProcessBeforeInitialization,color为空,设置默认颜色为黑色");
                car.setColor("黑色");
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("car".equals(beanName)){
            Car car = (Car)bean;
            if(car.getMaxSpeed()>=200){
                System.out.println("调用BeanPostProcessor.postProcessAfterInitialization.速度大于200,调整为200");
                car.setMaxSpeed(200);
            }
        }

        return bean;
    }
}
