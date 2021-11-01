package com.sxd.swapping.globalException.controller;

import com.sxd.swapping.globalException.param.GlobalParams;
import com.sxd.swapping.globalException.result.GlobalResult;
import com.sxd.swapping.mybatis.pojo.HuaYangArea;
import com.sxd.swapping.mybatis.service.HuaYangService;
import com.sxd.swapping.utils.page.MyPage;
import com.sxd.swapping.utils.page.PageHelperUtil;
import com.sxd.swapping.utils.validator.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/myglobal/exception")
public class GlobalExceptionController {

    @Resource
    private  HuaYangService huaYangService;

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


    @ResponseBody
    @PostMapping(value = "/pageFind")
    public GlobalResult pageFind(@Valid @RequestBody GlobalParams params){

        log.info("比如当前是一个分页查询接口  查询时，前端一定要传入 currentPage  和 pageSize！！！");

        MyPage<HuaYangArea> page = new MyPage<>();
        page.setCurrentPage(params.getCurrentPage());
        page.setPageSize(params.getPageSize());

        log.info("业务查询结果可能是一个全量的list,利用分页工具，得到本次本页期望查到的这一页数据，即内存中分页！！！");
        List<HuaYangArea> list = huaYangService.selectLikeNameWithMyBatis("查询条件");
        List<HuaYangArea> pageList = PageHelperUtil.toPage(list, page);
        log.info("PageHelperUtil.toPage的使用，不一定MyPage<HuaYangArea>泛型和List<HuaYangArea>泛型 一致，可以不一样，这个工具仅内存分页而已，可以" +
                "得到pageList后再assemble一个期望返回的结果集 比如门店信息可以完善对应商家信息后 再setResult中 ！！！");
        page.setResult(pageList);
        log.info("分页查询完成！！！");
        return GlobalResult.success();
    }

}
