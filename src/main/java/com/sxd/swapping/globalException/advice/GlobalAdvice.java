package com.sxd.swapping.globalException.advice;

import com.sxd.swapping.globalException.customException.ValidateArgumentException;
import com.sxd.swapping.globalException.result.GlobalResult;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常捕获
 *@RestControllerAdvice都是对Controller进行增强的，可以全局捕获spring mvc抛的异常。
 *
 * ExceptionHandler 可以全局仅捕获一种异常，也可以全局捕获多种异常，从上到下 依次处理
 *
 */
@RestControllerAdvice
public class GlobalAdvice {


    /**
     * ExceptionHandler的作用是用来捕获指定的异常
     * 这里示例 捕获 Java的validation做入参的校验 的校验失败的异常
     * 统一处理，免得返回前端
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GlobalResult handleStoreAuthException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder detailMsg = new StringBuilder();
        if (CollectionUtils.isNotEmpty(allErrors)) {
            allErrors.stream().forEach(i -> detailMsg.append(i.getDefaultMessage()).append(";"));
        }
        return GlobalResult.build(GlobalResult.ERROR_CODE, detailMsg.toString());
    }

    /**
     * 自定义参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GlobalResult handleStoreAuthException(ValidateArgumentException e) {
        return GlobalResult.build(GlobalResult.ERROR_CODE, e.getMsg());
    }


    /**
     * ExceptionHandler的作用是用来捕获指定的异常
     * 这里示例 捕获 Exception异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public GlobalResult handleStoreAuthException(Exception e) {
        return GlobalResult.build(GlobalResult.ERROR_CODE, e.getMessage());
    }
}
