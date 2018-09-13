package com.smart.test;

public class Test {
    int a = 10;
    String b = "b";

    @Override
    public String toString() {
        return "Test{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String s= null;
        String t = null + "";
        System.out.println(t == s);
        System.out.println(null == null);
        //System.out.println(new Test());
        String ss = null + "1";
        System.out.println(ss);
        System.out.println(null+"2");
        System.out.println("--------------------------");
        Object o = null + "1";
        System.out.println( o instanceof Object );
        System.out.println( o instanceof String );
        System.out.println( o.toString() );
        System.out.println( o );
        System.out.println("============================");
        System.out.println(new Object());
    }
}
