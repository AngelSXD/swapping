package com.sxd.swapping.tools.validation.controller;

import com.sxd.swapping.tools.validation.query.BaseRequestBean;
import com.sxd.swapping.tools.validation.dto.ValidationResultDTO;
import com.sxd.swapping.tools.validation.util.ValidationUtil;
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
public class ValidationController {



    @RequestMapping(value = "/myTest2", method = {RequestMethod.GET,RequestMethod.POST})
    public String myTestController2(@RequestBody BaseRequestBean baseRequestBean){

        StringBuilder msg = new StringBuilder("访问成功");
        ValidationResultDTO validationResultDTO = ValidationUtil.validateEntity(baseRequestBean);
        if (validationResultDTO.isHasErrors()){
            msg.setLength(0);
            msg.append(validationResultDTO.getDetailErrorMsg());
        }

        return msg.toString();
    }


    @RequestMapping(value = "/myTest",method = {RequestMethod.GET,RequestMethod.POST})
    public String myTestController(){
        System.out.println("进入controller方法");
        return "访问成功";
    }
}
