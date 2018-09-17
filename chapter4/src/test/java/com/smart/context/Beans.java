package com.smart.context;

import com.smart.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zy
 * @DATE 2018/9/15  10:50
 */

//标识一个配置信息提供类
@Configuration
public class Beans {

    //定义一个Bean
    @Bean(name="car")
    public Car buildCar(){
        Car car = new Car();
        car.setBrand("红旗CA72");
        car.setMaxSpeed(200);
        return car;
    }
}