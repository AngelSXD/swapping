package com.sxd.swapping.globalException.customException;

public class ValidateArgumentException extends RuntimeException {
    private String msg;
    public ValidateArgumentException() {

    }

    public ValidateArgumentException(String msg) {
        super(msg);
        setMsg(msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
