package com.smart.aop;

public interface BeanSelfProxyAware {
    void setSelfProxy(Object object);  //织入自身代理类接口
}