package com.sxd.swapping.controller;

import com.sxd.swapping.base.PageResponse;
import com.sxd.swapping.base.UniVerResponse;
import com.sxd.swapping.domain.HuaYangArea;
import com.sxd.swapping.service.HuaYangService;
import com.sxd.swapping.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/huayang")
public class HuaYangApi {


    @Autowired
    HuaYangService huaYangService;
    /**
     * 保存 数据 API
     * @param huaYangArea
     * @return
     */
    @PostMapping()
    public UniVerResponse<HuaYangArea> huayang(@RequestBody HuaYangArea huaYangArea){
        UniVerResponse.checkField(huaYangArea,"areaName","areaPerson");

        UniVerResponse<HuaYangArea> response = new UniVerResponse<>();
        try {
            huaYangArea.initEntity();
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
