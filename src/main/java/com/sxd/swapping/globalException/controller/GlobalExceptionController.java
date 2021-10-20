package com.sxd.swapping.globalException.controller;

import com.sxd.swapping.globalException.param.GlobalParams;
import com.sxd.swapping.globalException.result.GlobalResult;
import com.sxd.swapping.utils.validator.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/myglobal/exception")
public class GlobalExceptionController {


    @ResponseBody
    @PostMapping(value = "/test")
    public GlobalResult myTest(@Valid @RequestBody GlobalParams params){

        Long id = params.getId();
        id = id / 0;
        log.info("业务处理！！！");

        ValidatorUtil.equals(params.getNum1() > params.getNum2(), true , "num1必须大于num2");
        log.info("业务处理！！！");
        return GlobalResult.success();
    }


}
