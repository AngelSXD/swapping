package com.sxd.swapping.globalException.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 使用 Java的validation做入参的校验
 * 测试一下全局异常怎么捕获这种入参类型的校验（因为此处入参校验未通过，压根就不会进Controller）
 */
@Data
public class GlobalParams {

    @NotNull(message = "ID不能为空")
    private Long id;

    @NotEmpty(message = "集合不能为空")
    private List<String>  myStrList;



}
