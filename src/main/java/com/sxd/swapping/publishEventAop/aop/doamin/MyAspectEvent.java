package com.sxd.swapping.publishEventAop.aop.doamin;

import com.sxd.swapping.publishEventAop.enums.MyEnum;
import org.springframework.context.ApplicationEvent;

public class MyAspectEvent  extends ApplicationEvent {


    MyEnum[] myEnums;

    String myName;

    Object[] requertParams;

    Object returnVal;

    public MyAspectEvent(Object source) {
        super(source);
    }




    public MyEnum[] getMyEnums() {
        return myEnums;
    }

    public void setMyEnums(MyEnum[] myEnums) {
        this.myEnums = myEnums;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public Object[] getRequertParams() {
        return requertParams;
    }

    public void setRequertParams(Object[] requertParams) {
        this.requertParams = requertParams;
    }

    public Object getReturnVal() {
        return returnVal;
    }

    public void setReturnVal(Object returnVal) {
        this.returnVal = returnVal;
    }
}
