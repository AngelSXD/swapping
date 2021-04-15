package com.sxd.swapping.publishEventAop.aop.handler;

import com.sxd.swapping.publishEventAop.aop.doamin.MyAspectEvent;
import org.springframework.stereotype.Component;

/**
 * 第二种业务上的补充逻辑
 */
@Component
public class MyTwoOperationHandler  extends MyAbstractOperationHandler {


    @Override
    public void handleBusiness(MyAspectEvent operateEvent) {


        String myName = operateEvent.getMyName();

        //获取到切面方法的入参
        Object[] requertParams = operateEvent.getRequertParams();

        //获取到切面方法的出参
        Object returnVal = operateEvent.getReturnVal();

        System.out.println("第二种补充业务-打印方法入参：" + requertParams[0].toString());
        System.out.println("做第二种补充业务："+myName);
        System.out.println("第二种补充业务-打印方法出参:"+ returnVal.toString());

    }
}
