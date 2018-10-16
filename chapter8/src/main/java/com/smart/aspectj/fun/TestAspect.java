package com.smart.aspectj.fun;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import com.smart.Monitorable;
/**
 * 说明：需要测试某个切点函数时，取消相应的注解就可以了。
 * @author 陈雄华
 *
 */
@Aspect
public class TestAspect implements Ordered{
    @Before("execution(public * *(..))") //public 第一个*表示返回类型 第二个*代表方法名 (无法匹配构造器,SmartSeller就无法被匹配)
	public void allPublicFun(){
	    System.out.println("allPublicFun() executed!");
	}

    @AfterReturning("execution(* *To(..))") //第一个* 只代表返回类型
    public void allToFun(){
    	System.out.println("allToFun() executed!");
    }

	//匹配Waiter接口的所有方法,匹配NaiveWaiter和NaughtyWaiter下的greetTo()和serverTo()方法,
	// 第一个*表示返回类型任意类型,后一个*表示Waiter接口中的所有方法
    @Before("execution(* com.smart.Waiter.*(..))")
    public void allWaiterFun(){
    	System.out.println("allWaiterFun() executed!");
    }

    //匹配Waiter接口所有的方法,除此之外,还要有实现类的其他非接口的方法,即匹配Waiter接口即其所有实现类的方法
    @Before("execution(* com.smart..Waiter+.*(..))")
    public void allChildClassFun(){
    	System.out.println("allChildClassFun() executed!");
    }

//	@Before("execution(* joke(Object,int)))")

	@Before("args(Object,*)")
    public void jokeFun(){
    	System.out.println("jokeFun() executed!");
    }

    @AfterReturning("@annotation(com.smart.anno.NeedTest)")//后置增强切面,即对@NeedTest这个annotation的方法进行增强
    public void needTestFun(){
    	System.out.println("needTestFun() executed!");
    }

    @AfterReturning("args(com.smart.Waiter)") //等同于 @AfterReturning("execution(com.smart.Waiter+)")
    public void argsTest(){
    	System.out.println("argsTest() executed!");
    }

    @AfterReturning("@args(com.smart.Monitorable)")
    public void atArgsTest(){
    	System.out.println("atArgsTest() executed!");
    }

	//within最小粒度只能到类,无法到具体某个方法, todo 但是此处毫无意义,Waiter本身是接口,无法实例化
    @Before("within(com.smart.Waiter)")
    public void withinTest(){
    	System.out.println("withinTest() executed!");
    }

    @Before("@within(com.smart.Monitorable)")
	public void atWithinTest() {
		System.out.println("atWithinTest() executed!");
	}

	@AfterReturning("this(com.smart.Seller)")
	public void thisTest(){
		System.out.println("thisTest() executed!");
	}

	public int getOrder() {
		return 1;
	}
}
