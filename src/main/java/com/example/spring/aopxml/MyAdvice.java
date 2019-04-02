package com.example.spring.aopxml;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 想要实现aop必须要满足三个条件
 * 1.需要生成代理对象，使用spring的切点表达式即可生成
 * 2.需要通知，也就是需要增强的代码，比如事务的开启关闭回滚
 * 3.被代理对象
 * 通过切点表达式，将通知放入到被代理对象的方法对应位置
 *
 * 当前为需要增强的代码，又叫做通知
 * Created by Administrator on 2019/3/31/031.
 */
public class MyAdvice {

    public void before(){
        System.out.println("前置通知");
    }

    public void afterReturnning(){
        System.out.println("后置通知(如果发生异常将不会执行)");
    }

    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知前");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("环绕通知后");
        return proceed;
    }

    public void after(){
        System.out.println("后置通知");
    }

    public void excep(){
        System.out.println("发生异常将会执行");
    }

}
