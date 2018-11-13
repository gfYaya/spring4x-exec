package com.smart.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* 测试@RequestMapping 相同path 不同method是否报错

 */

@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test1(){
        System.out.println("test1 get");
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public void test2(){
        System.out.println("test2 post");
    }
}
