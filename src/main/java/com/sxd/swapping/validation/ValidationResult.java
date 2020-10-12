package com.sxd.swapping.validation;

import java.util.Map;

/**
 * 校验结果体
 */
public class ValidationResult {

    //校验结果是否有错  
    private boolean hasErrors = false;

    //校验错误信息  
    private Map<String, String> errorMsg;

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Map<String, String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getDetailErrorMsg() {
        return null != errorMsg ? errorMsg.toString() : null;
    }

    @Override
    public String toString() {
        return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg=" + errorMsg + "]";
    }
}
