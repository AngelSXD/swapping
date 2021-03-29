package com.sxd.swapping.service.impl;

import com.sxd.swapping.annotation.MyAnno;
import com.sxd.swapping.domain.BaseRequestBean;
import com.sxd.swapping.enums.MyEnum;
import com.sxd.swapping.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {



    @Override
    @MyAnno(myName = "德玛西亚", myEnums = {MyEnum.ONE,MyEnum.TWO})
    public String saveBaseInfo(String  param) {
        BaseRequestBean baseRequestBean = new BaseRequestBean();
        System.out.println("进入被切方法");
        return "方法出参";
    }



}
