package com.smart.aspectj.advanced;

import org.aspectj.lang.annotation.Pointcut;

//@Pointcut 等同于自定义切点?e.g: @Before("TestNamePointcut.inPkgGreetTo()") ,这里面未用到execution,args等其他语法,而是在该函数的annotation中重新定义了一遍
public class TestNamePointcut {
    //方法可视域修饰符为private,表明该命名切点只能在本切面类使用 如下inPkgGreetTo 方法中的annotation使用
	@Pointcut("within(com.smart.*)")
	private void inPackage(){}

	//方法可视域修饰符为protected,表名该命名切点可以在当前包中的切面类、子切面类使用
	@Pointcut("execution(* greetTo(..)))")
    protected void greetTo(){}

    @Pointcut("inPackage() && greetTo()")
    public void inPkgGreetTo(){}
}
