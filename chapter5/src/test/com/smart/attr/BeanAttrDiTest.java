package com.smart.attr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;

public class BeanAttrDiTest {
    private ApplicationContext factory = null;
    private static String[] CONFIG_FILES = { "com/smart/attr/beans.xml" };

    @BeforeClass
    public void setUp() throws Exception{
        factory = new ClassPathXmlApplicationContext(CONFIG_FILES);
    }



}
