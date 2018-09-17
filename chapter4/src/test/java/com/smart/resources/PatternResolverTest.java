package com.smart.resources;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

//maven的test下使用单元测试能够使用maven的main/resources下的资源
public class PatternResolverTest {

    @Test
    public void getResources() throws Throwable{
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //加载所有类包com.smart(及子孙包)下以.xml为后缀的资源
        Resource resources[] = resolver.getResources("classpath*:com/smart/**/*.xml");
        assertNotNull(resources);
        for(Resource resource:resources){
            //jar包中的配置文件获取不要用getFile(),而是getInputStream();
            System.out.println(resource.getDescription());
        }
    }

}
