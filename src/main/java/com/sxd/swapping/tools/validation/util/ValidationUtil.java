package com.sxd.swapping.tools.validation.util;

import com.sxd.swapping.tools.validation.dto.ValidationResultDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: SXD
 * @Description:  校验工具类
 * @Date: create in 2020/1/10 14:35
 */
public class ValidationUtil {

    private static final String OBJ_NULL = "Para";

    private static final String OBJ_NULL_ERR_MSG = "validated Object is null";


    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 验证指定对象
     *
     * @param obj
     *            需要被验证对象
     * @return
     */
    public static <T> ValidationResultDTO validateEntity(T obj) {
        ValidationResultDTO result = new ValidationResultDTO();
        if(!checkObjNull(obj, result)){
            Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
            if (CollectionUtils.isNotEmpty(set)) {
                result.setHasErrors(true);
                Map<String, String> errorMsg = new HashMap<String, String>();
                for (ConstraintViolation<T> cv : set) {
                    errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
                }
                result.setErrorMsg(errorMsg);
            }
        }

        return result;
    }


    /**
     * 验证指定对象的指定属性
     *
     * @param obj
     *            需要被验证对象
     * @param propertyName
     *            需要验证的属性名称
     * @return
     */
    public static <T> ValidationResultDTO validateProperty(T obj, String... propertyName) {
        ValidationResultDTO result = new ValidationResultDTO();
        if(!checkObjNull(obj, result)){
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (String pName : propertyName) {
                Set<ConstraintViolation<T>> set = validator.validateProperty(obj, pName, Default.class);
                if (CollectionUtils.isNotEmpty(set)) {
                    result.setHasErrors(true);

                    for (ConstraintViolation<T> cv : set) {
                        errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
                    }

                }
            }
            result.setErrorMsg(errorMsg);
        }

        return result;
    }

    /**
     * 验证指定对象
     *
     * @param obj
     *            需要被验证对象
     * @param exceptPropertyName
     *            排除属性(不希望验证的属性)
     * @return
     */
    public static <T> ValidationResultDTO validateEntity(T obj, String... exceptPropertyName) {
        ValidationResultDTO result = new ValidationResultDTO();
        if(!checkObjNull(obj, result)){
            Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
            if (CollectionUtils.isNotEmpty(set)) {
                Map<String, String> errorMsg = new HashMap<String, String>();
                for (ConstraintViolation<T> cv : set) {
                    String field = cv.getPropertyPath().toString();
                    if (!isExcept(field, exceptPropertyName)) {
                        result.setHasErrors(true);
                        errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
                    }
                }
                result.setErrorMsg(errorMsg);
            }
        }

        return result;
    }

    /**
     *
     * 判断字段是否属于例外字段列表
     *
     * @param field
     * @param exceptFieldName
     * @return true:属于例外字段 false:不是例外字段
     * @exception
     * @since 1.0.0
     */
    private static boolean isExcept(String field, String... exceptFieldName) {
        for (String ef : exceptFieldName) {
            if (StringUtils.isNotBlank(ef) && ef.equalsIgnoreCase(field)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查入参是否为null
     * @param obj
     * @param result
     * @param <T>
     * @return
     */
    private static <T> boolean checkObjNull(T obj, ValidationResultDTO result) {
        if (null == obj){
            Map<String, String> errorMsg = new HashMap<String, String>();
            errorMsg.put(OBJ_NULL, OBJ_NULL_ERR_MSG);
            result.setErrorMsg(errorMsg);
            return true;
        }
        return false;
    }
}
