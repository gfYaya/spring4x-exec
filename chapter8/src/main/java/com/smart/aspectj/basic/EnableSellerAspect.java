package com.smart.aspectj.basic;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

import com.smart.Seller;
import com.smart.SmartSeller;


@Aspect
public class EnableSellerAspect {
   @DeclareParents(value="com.smart.NaiveWaiter", //为NaiveWaiter这个类添加需要实现的接口
           defaultImpl=SmartSeller.class) //默认的接口实现类
   public  Seller seller;  //要实现的目标接口
}
