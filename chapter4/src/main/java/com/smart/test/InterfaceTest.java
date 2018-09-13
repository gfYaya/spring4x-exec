package com.smart.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//测试接口是否能反射
//result:接口无法实例化,也就是说Spring-Mybatis用的mapper-scan 那些dao的interface接口用的不是JDK反射实例化出来
// 的实例,应该是mapper.xml与对应的interface结合重新生成的bean?
public class InterfaceTest {

    public YayaInterface soutAPI(){
        return new YayaInterface() {
            public void sout() {
                System.out.println("Yaya");
            }
        };
    }

    public static void main(String[] args) {
        InterfaceTest ift = new InterfaceTest();
        YayaInterface yif = ift.soutAPI();
        yif.sout(); //Yaya

        //通过类加载器获取 接口对象?
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = null;
        try {
            clazz = loader.loadClass("com.smart.test.YayaInterface");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //获取默认构造函数并尝试通过它对这个接口进行 实例化
        try {
            Constructor cons = clazz.getDeclaredConstructor(null); //java.lang.NoSuchMethodException: com.smart.test.YayaInterface.<init>()
            YayaInterface yif2 = (YayaInterface)cons.newInstance();
            yif2.sout();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}

interface YayaInterface{
    public void sout();
}
