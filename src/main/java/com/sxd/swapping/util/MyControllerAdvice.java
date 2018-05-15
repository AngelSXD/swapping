package com.sxd.swapping.util;

import com.sxd.swapping.base.UniVerResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public UniVerResponse<String> errorHandler(Exception ex) {
        ex.printStackTrace();
        UniVerResponse<String> response = new UniVerResponse<>();
        response.beFalse("操作失败",UniVerResponse.ERROR_BUSINESS,null);
        return response;
    }

    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public UniVerResponse<String> myErrorHandler(MyException ex) {
        ex.printStackTrace();
        UniVerResponse<String> response = new UniVerResponse<>();
        response.beFalse(ex.getMessage(),UniVerResponse.ERROR_BUSINESS,null);
        return response;
    }
}
