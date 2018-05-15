package com.sxd.swapping.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 */
@Getter
@Setter
public class MyException extends  RuntimeException {

    private int code;

    public MyException(String msg){
        super(msg);
    }

    public MyException(String msg,int code){
        super(msg);
        this.code = code;
    }

}
