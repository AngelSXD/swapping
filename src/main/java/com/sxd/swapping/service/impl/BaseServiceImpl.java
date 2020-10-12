package com.sxd.swapping.service.impl;

import com.sxd.swapping.annotation.MyAnno;
import com.sxd.swapping.domain.BaseRequestBean;
import com.sxd.swapping.enums.MyEnum;
import com.sxd.swapping.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BaseServiceImpl implements BaseService {



    @Override
    @MyAnno(myName = "德玛西亚", myEnums = {MyEnum.ONE,MyEnum.TWO})
    public void saveBaseInfo() {
        BaseRequestBean baseRequestBean = new BaseRequestBean();
        System.out.printf("进入被切方法");
    }



}
