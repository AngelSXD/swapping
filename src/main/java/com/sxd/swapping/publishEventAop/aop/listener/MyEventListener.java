package com.sxd.swapping.publishEventAop.aop.listener;


import com.sxd.swapping.publishEventAop.aop.context.MyOperationHandlerContext;
import com.sxd.swapping.publishEventAop.aop.doamin.MyAspectEvent;
import com.sxd.swapping.publishEventAop.aop.handler.MyAbstractOperationHandler;
import com.sxd.swapping.publishEventAop.enums.MyEnum;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *监听者模式  的监听者对象
 */
@Component
public class MyEventListener {

    @Resource
    private MyOperationHandlerContext operationHandlerContext;

    @Resource
    private ThreadPoolTaskExecutor taskExecutor1;

    //使用@Async指定线程池 的 异步调用 https://www.jb51.net/article/105214.htm
    @Async("taskExecutor1")
    @EventListener
    public void listener(MyAspectEvent myAspectEvent){
        //获取到 自定义注解上的属性
        MyEnum[] myEnums = myAspectEvent.getMyEnums();

        //根据自定义注解 使用处 指定的补充事件枚举项，分别执行 对应的具体补充事件handler实现的逻辑
        for (MyEnum myEnum : myEnums) {
            MyAbstractOperationHandler operationHandler = operationHandlerContext.getOperationHandler(myEnum);
            taskExecutor1.execute(() -> operationHandler.handleBusiness(myAspectEvent));
        }

    }
}
