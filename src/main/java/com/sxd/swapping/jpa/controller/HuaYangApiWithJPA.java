package com.sxd.swapping.jpa.controller;

import com.sxd.swapping.jpa.base.BatchBean;
import com.sxd.swapping.mybatis.vo.PageResponse;
import com.sxd.swapping.mybatis.vo.UniVerResponse;
import com.sxd.swapping.mybatis.pojo.HuaYangArea;
import com.sxd.swapping.mybatis.service.HuaYangService;
import com.sxd.swapping.mybatis.exception.MyException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 展示swagger注解使用在新建分支上
 */
@Api(value = "HuaYangAPiWithJPA",description = "对华阳对象相关操作",tags = {"华阳JPA"})
@RestController
@RequestMapping(value = "/huayang")
public class HuaYangApiWithJPA {


    @Autowired
    HuaYangService huaYangService;
    /**
     * 保存 数据 API
     * @param huaYangArea
     * @return
     */
    @ApiOperation(value = "保存HUAYANG信息",notes = "传入HUAYANG实体，保存HUAYANG信息")
    @PostMapping()
    @ApiResponses({
            @ApiResponse(code = 200,message = "success"),
            @ApiResponse(code = 10001,message = "参数错误"),
            @ApiResponse(code = 20001,message = "业务错误"),
            @ApiResponse(code = 50001,message = "系统异常")})
    public UniVerResponse<HuaYangArea> huayang(@ApiParam(value = "create a entity  HuaYangArea") @RequestBody HuaYangArea huaYangArea){
        UniVerResponse.checkField(huaYangArea,"areaName","areaPerson");

        UniVerResponse<HuaYangArea> response = new UniVerResponse<>();
        try {
            huaYangArea.initEntity();
            System.out.println("jpa测试java初始化时间："+huaYangArea.getCreateDate());
            response.beTrue(huaYangService.insert(huaYangArea));
        } catch (Exception e) {
            throw new MyException("插入失败",UniVerResponse.ERROR_BUSINESS);
        }
        return response;
    }


    /**
     * 模糊查询符合条件的数据
     * @param areaName
     * @return
     */
    @GetMapping(value = "/huayangs")
    public UniVerResponse<List<HuaYangArea>> huayangs(String areaName){
        UniVerResponse.simplCheckField(areaName,"areaName");
        UniVerResponse<List<HuaYangArea>> response = new UniVerResponse<>();
        HuaYangArea huaYangArea = new HuaYangArea();
        huaYangArea.setAreaName(areaName);
        try {
            List<HuaYangArea> list = huaYangService.find(huaYangArea);
            response.beTrue(list);
        } catch (Exception e) {
            throw new MyException("查询失败",UniVerResponse.ERROR_BUSINESS);
        }
        return response;
    }

    @PutMapping("/update")
    public UniVerResponse<HuaYangArea> update(@RequestBody HuaYangArea huaYangArea){
        UniVerResponse.checkField(huaYangArea,"uid");
        UniVerResponse<HuaYangArea> response = new UniVerResponse<>();

        try {
            huaYangArea.updateEntity();
            huaYangArea = huaYangService.update(huaYangArea);
            response.beTrue(huaYangArea);
        } catch (Exception e) {
            throw new MyException("更新失败",UniVerResponse.ERROR_BUSINESS);
        }

        return response;
    }

    /**
     * 自定义的bean 需要使用@RequestBody标注 否则 仅会提示 第一个字段不能为null
     * @param batchBean
     * @return
     */
    @PostMapping("/updates")
    public UniVerResponse<List<HuaYangArea>> updates(@RequestBody BatchBean batchBean){
        UniVerResponse.checkField(batchBean,"likeName","areaPersons");
        UniVerResponse<List<HuaYangArea>> response = new UniVerResponse<>();
        List<Long> areaPersons = batchBean.getAreaPersons();

        List<HuaYangArea> list = new ArrayList<>();
        areaPersons.forEach(i->{
            HuaYangArea huaYangArea = new HuaYangArea();
            huaYangArea.initEntity();
            huaYangArea.setAreaName(batchBean.getLikeName());
            huaYangArea.setAreaPerson(i);
            list.add(huaYangArea);
        });
        try {
            response.beTrue(huaYangService.updates(batchBean.getLikeName(),list));
        }catch (Exception e){
            throw new MyException("删除再插入失败",UniVerResponse.ERROR_BUSINESS);
        }
        return response;
    }

    @GetMapping("/pages")
    public PageResponse<HuaYangArea> pages(HuaYangArea huaYangArea){
        PageResponse<HuaYangArea> pageResponse = new PageResponse<>();
        Map<String,Sort.Direction> map = new HashMap<>();
        map.put("areaName", Sort.Direction.DESC);
        map.put("areaPerson",Sort.Direction.DESC);
        try {
            Page<HuaYangArea> pages = huaYangService.pageSortFind(huaYangArea,map);
            pageResponse.beTrue(pages.getTotalElements(),pages.getContent());
        } catch (Exception e) {
            throw new MyException("查询失败",UniVerResponse.ERROR_BUSINESS);
        }
        return pageResponse;
    }


}
