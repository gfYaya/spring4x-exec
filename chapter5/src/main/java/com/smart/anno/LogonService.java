package com.smart.anno;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

//todo: 该Component也是一个Bean,该Bean以什么方式注入(属性注入,构造器注入,还是Field上直接注入),取决于@Autowired放在什么位置
@Service
public class LogonService implements BeanNameAware {

    //@Lazy //延迟注入,只有调用到该属性的时候才进行注入该属性的值
    //@Autowired(required=false)
    /* 如果容器中没有一个和标注的变量类型匹配的Bean,那么Spring容器启动的时候会报出NoSuchBeanDefinitionException异常.如果希望即使找不到匹配的Bean
    完成注入也不要抛出异常,可以使用@Autowired(required=false)来标注
     */
    //private LogDao logDao;
    public LogDao logDao;


    //@Autowired
    //@Qualifier("userDao") //注入类型为UserDao,名为userDao类型的Bean
    // 如果容器中有一个以上匹配的Bean的时候,则可以通过@Qualifier注解限定bean的名称
    //private UserDao userDao;
    public UserDao userDao;

    public LogonService(){

    }

    /*@Autowired
    public LogonService(@Qualifier("userDao")UserDao userDao,LogDao logDao){
        this.userDao = userDao;
        this.logDao = logDao;
    }*/


    public void saveLog(){
        logDao.saveLog();
    }

    @Autowired
    /*自动将LogDao传给方法入参.如果一个方法拥有多个入参,在默认情况下,Spring将自动选择匹配入参的类型进行注入.
    加了Autowired,在注入的时候会自动执行该方法.*/
    public void setLogDao(LogDao logDao) {
        this.logDao = logDao;
    }

    @Autowired
    @Qualifier("userDao")
    public void setUserDao(UserDao userDao) {
        System.out.println("auto inject");
        this.userDao = userDao;
    }

    @Autowired
    public void init(@Qualifier("userDao")UserDao userDao,LogDao logDao){
            System.out.println("multi param inject");
            this.userDao = userDao;
            this.logDao =logDao;
    }

    public LogDao getLogDao() {
        return logDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setBeanName(String beanName) {
        System.out.println("beanName:" + beanName);
    }

    public void initMethod1() {
        System.out.println("initMethod1");
    }

    public void initMethod2() {
        System.out.println("initMethod2");
    }


}
