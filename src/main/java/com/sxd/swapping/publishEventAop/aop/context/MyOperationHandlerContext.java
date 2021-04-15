package com.sxd.swapping.publishEventAop.aop.context;

import com.sxd.swapping.publishEventAop.aop.handler.MyAbstractOperationHandler;
import com.sxd.swapping.publishEventAop.aop.handler.MyOneOperationHandler;
import com.sxd.swapping.publishEventAop.aop.handler.MyTwoOperationHandler;
import com.sxd.swapping.publishEventAop.enums.MyEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取业务补充逻辑  上下文
 */
@Component
public class MyOperationHandlerContext  implements ApplicationContextAware {

    private Map<MyEnum, MyAbstractOperationHandler> operationHandlerMap;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (operationHandlerMap == null) {
            operationHandlerMap = new HashMap<>();
            operationHandlerMap.put(MyEnum.ONE,applicationContext.getBean(MyOneOperationHandler.class));
            operationHandlerMap.put(MyEnum.TWO,applicationContext.getBean(MyTwoOperationHandler.class));
        }
    }

    public MyAbstractOperationHandler getOperationHandler(MyEnum myEnum){
        return operationHandlerMap.get(myEnum);
    }

}
