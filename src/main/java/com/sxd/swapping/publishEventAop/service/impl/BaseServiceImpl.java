package com.sxd.swapping.publishEventAop.service.impl;

import com.sxd.swapping.publishEventAop.annotation.MyAnno;
import com.sxd.swapping.publishEventAop.enums.MyEnum;
import com.sxd.swapping.publishEventAop.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {



    @Override
    @MyAnno(myName = "德玛西亚", myEnums = {MyEnum.ONE,MyEnum.TWO})
    public String saveBaseInfo(String  param) {
        System.out.println("进入被切方法");
        return "方法出参";
    }

}
