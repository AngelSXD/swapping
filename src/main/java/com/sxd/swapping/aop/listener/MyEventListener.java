package com.sxd.swapping.aop.listener;


import com.sxd.swapping.aop.doamin.MyAspectEvent;
import com.sxd.swapping.enums.MyEnum;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *监听者模式  的监听者对象
 */
@Component
public class MyEventListener {


    @Async
    @EventListener
    public void listener(MyAspectEvent myAspectEvent){
        //获取到 自定义注解上的属性
        MyEnum[] myEnums = myAspectEvent.getMyEnums();
        String myName = myAspectEvent.getMyName();

        //获取到切面方法的入参
        Object[] requertParams = myAspectEvent.getRequertParams();

        //获取到切面方法的出参
        Object returnVal = myAspectEvent.getReturnVal();
        for (MyEnum myEnum : myEnums) {
            if (myEnum == MyEnum.ONE) {
                System.out.printf("做第一件事情");
            }
            if (myEnum == MyEnum.TWO) {
                System.out.printf("做第二件事件");
            }

            if(myEnum == MyEnum.ALL) {
                System.out.printf("做所有的事情");
            }
        }

    }
}
