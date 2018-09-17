package com.smart.resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertNotNull;

//warnings 如果是pom.xml的test依赖的scope为test 那么只能将单元测试类 写在maven的test/java下面
public class PatternResolverTest {

    @Test
    public void getResources() throws Throwable{
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //加载所有类包com.smart(及子孙包)下以.xml为后缀的资源
        Resource resources[] = resolver.getResources("classpath*:com/smart/**/*.xml");
        assertNotNull(resources);
        for(Resource resource:resources){
            System.out.println(resource.getDescription());
        }
    }

}
