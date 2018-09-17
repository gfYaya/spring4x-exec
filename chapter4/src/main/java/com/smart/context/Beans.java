package com.smart.context;

import com.smart.Car;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    public Car buildCar(){
        Car car = new Car();
        car.setBrand("红旗CA72");
        car.setMaxSpeed(200);
        return car;
    }

}
