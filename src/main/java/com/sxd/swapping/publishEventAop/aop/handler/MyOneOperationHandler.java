package com.sxd.swapping.publishEventAop.aop.handler;

import com.sxd.swapping.publishEventAop.aop.doamin.MyAspectEvent;
import org.springframework.stereotype.Component;

/**
 * 第一种业务上补充逻辑
 */
@Component
public class MyOneOperationHandler extends MyAbstractOperationHandler {

    @Override
    public void handleBusiness(MyAspectEvent operateEvent) {

        String myName = operateEvent.getMyName();

        //获取到切面方法的入参
        Object[] requertParams = operateEvent.getRequertParams();

        //获取到切面方法的出参
        Object returnVal = operateEvent.getReturnVal();

        System.out.println("第一种补充业务-打印方法入参：" + requertParams[0].toString());
        System.out.println("做第一种补充业务："+myName);
        System.out.println("第一种补充业务-打印方法出参:"+ returnVal.toString());


    }

}
