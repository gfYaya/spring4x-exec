package com.smart.context

import com.smart.Car

/**
 * @author zy
 * @DATE 2018/9/15  12:31
 */

beans {
    car(Car) {//名字(类型)
        brand = "红旗CA72"
        maxSpeed = "200"
        color = "red"
    }
}