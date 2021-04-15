package com.sxd.swapping.publishEventAop.controller;

import com.sxd.swapping.publishEventAop.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishEventController {

    @Autowired
    BaseService baseService;

    @RequestMapping(value = "/aoptest", method = {RequestMethod.GET})
    public String myAopTest(){
        baseService.saveBaseInfo("方法入参");
        return "测试成功";
    }
}
