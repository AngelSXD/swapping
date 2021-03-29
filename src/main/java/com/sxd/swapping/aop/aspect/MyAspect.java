package com.sxd.swapping.aop.aspect;

import com.sxd.swapping.annotation.MyAnno;
import com.sxd.swapping.aop.doamin.MyAspectEvent;
import com.sxd.swapping.enums.MyEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * AOP 切面编程
 * Spring的publish-event  监听者模式
 *
 * 另一种方案：https://blog.csdn.net/weixin_43770545/article/details/105971971
 */
@Component
@Aspect
public class MyAspect implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    //切点在加了该注解的方法上
    @Pointcut("@annotation(com.sxd.swapping.annotation.MyAnno)")
    public void myCutPoint(){
    }

    /**
     * 方法执行后  进入切面
     *
     * 监听者模式 将事件发布出去
     *
     * @param joinPoint 切点
     * @param returnVal 被切方法的范围值
     */
    @AfterReturning(value = "myCutPoint()", returning = "returnVal")
    public void afterMethodDo(JoinPoint joinPoint,Object returnVal){
        //被切方法的入参
        Object[] args = joinPoint.getArgs();
        //被代理方法信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取方法上的自己的注解 及 信息
        MyAnno annotation = method.getAnnotation(MyAnno.class);
        MyEnum[] myEnums = annotation.myEnums();
        String myName = annotation.myName();


        MyAspectEvent event = new MyAspectEvent(applicationContext);
        event.setRequertParams(args);
        event.setReturnVal(returnVal);
        event.setMyName(myName);
        event.setMyEnums(myEnums);

        // 发布事件对象
        applicationContext.publishEvent(event);
    }


}
