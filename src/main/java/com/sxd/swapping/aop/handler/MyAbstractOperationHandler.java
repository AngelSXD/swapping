package com.sxd.swapping.aop.handler;

import com.sxd.swapping.aop.doamin.MyAspectEvent;

/**
 * 抽象的业务补充逻辑类
 */
public abstract class MyAbstractOperationHandler {

    /**
     * 具体的业务处理方法
     * 交由不同业务的具体子类去 实现各自的业务逻辑
     *
     * @param operateEvent
     */
    public abstract void handleBusiness(MyAspectEvent operateEvent);
}
