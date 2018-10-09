package com.smart.test;

public class A {
    static B b = new B();

    static {
        b.sayHello();  // null point ?并未出现,new A(),new B()两个都执行了,并且未产生阻塞
    }

    public static void main(String[] args) {
        new A();
        new B();
    }
}

class B {

    static A a = new A();


    public B() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sayHello(){
        System.out.println("B say hello");
    }
}
