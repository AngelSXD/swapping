package com.sxd.swapping.controller;

import com.sxd.swapping.domain.BaseRequestBean;
import com.sxd.swapping.service.BaseService;
import com.sxd.swapping.validation.ValidationResult;
import com.sxd.swapping.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: SXD
 * @Description:
 * @Date: create in 2019/10/9 15:09
 */
@RestController
public class FirstController {

    @Autowired
    BaseService baseService;

    @RequestMapping(value = "/aoptest", method = {RequestMethod.GET})
    public String myAopTest(){
        baseService.saveBaseInfo("方法入参");
        return "测试成功";
    }


    @RequestMapping(value = "/myTest2", method = {RequestMethod.GET,RequestMethod.POST})
    public String myTestController2(@RequestBody BaseRequestBean baseRequestBean){

        StringBuilder msg = new StringBuilder("访问成功");
        ValidationResult validationResult = ValidationUtil.validateEntity(baseRequestBean);
        if (validationResult.isHasErrors()){
            msg.setLength(0);
            msg.append(validationResult.getDetailErrorMsg());
        }

        return msg.toString();
    }


    @RequestMapping(value = "/myTest",method = {RequestMethod.GET,RequestMethod.POST})
    public String myTestController(){
        System.out.println("进入controller方法");
        return "访问成功";
    }
}
