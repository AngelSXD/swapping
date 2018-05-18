package com.sxd.swapping.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sxd.swapping.base.HuaYangModelBean;
import com.sxd.swapping.base.PageResponse;
import com.sxd.swapping.base.UniVerResponse;
import com.sxd.swapping.domain.HuaYangArea;
import com.sxd.swapping.service.HuaYangService;
import com.sxd.swapping.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/huayangMybatis")
public class HuaYangApiWithMyBatis {

    @Autowired
    HuaYangService huaYangService;

    @PostMapping("/insert")
    public UniVerResponse<HuaYangArea> insert(@RequestBody HuaYangArea huaYangArea){
        UniVerResponse.checkField(huaYangArea,"areaName","areaPerson");
        huaYangArea.initEntity();
        UniVerResponse<HuaYangArea> response = new UniVerResponse<>();
        try {
            huaYangArea = huaYangService.insertWithMybatis(huaYangArea);
            response.beTrue(huaYangArea);
        }catch (Exception e){
            throw  new MyException("插入失败",UniVerResponse.ERROR_BUSINESS);
        }

        return response;
    }

    @PutMapping("/update")
    public UniVerResponse<HuaYangArea> update(@RequestBody HuaYangArea huaYangArea){
        UniVerResponse.checkField(huaYangArea,"id","areaName","areaPerson");
        huaYangArea.updateEntity();
        UniVerResponse<HuaYangArea> response = new UniVerResponse<>();
        try {
            huaYangArea = huaYangService.updateWithMyBatis(huaYangArea);
            response.beTrue(huaYangArea);
        }catch (Exception e){
            throw  new MyException("更新失败",UniVerResponse.ERROR_BUSINESS);
        }

        return response;
    }

    /**
     * 不传入值的查询所有
     * @param areaName
     * @return
     */
    @GetMapping("/findByNameLike")
    public UniVerResponse<List<HuaYangArea>> findAll1(String areaName){

        UniVerResponse<List<HuaYangArea>> response = new UniVerResponse<>();
        try {
            response.beTrue(huaYangService.selectLikeNameWithMyBatis(areaName));
        }catch (Exception e){
            e.printStackTrace();
            throw  new MyException("查询失败",UniVerResponse.ERROR_BUSINESS);
        }

        return response;
    }

    /**
     * 不传入值  查不到结果
     * @param areaName
     * @return
     */
    @GetMapping("/findByNameLike2")
    public UniVerResponse<List<HuaYangArea>> findAll2(String areaName){

        UniVerResponse<List<HuaYangArea>> response = new UniVerResponse<>();
        try {
            response.beTrue(huaYangService.selectLikeNameWithMyBatis2(areaName));
        }catch (Exception e){
            e.printStackTrace();
            throw  new MyException("查询失败",UniVerResponse.ERROR_BUSINESS);
        }

        return response;
    }

    /**
     * 根据 业务主键获取
     * @param uid
     * @return
     */
    @GetMapping(value = "/oneHuaYang")
    public UniVerResponse<HuaYangArea> findOne(String uid){
        UniVerResponse.simplCheckField(uid,"uid");

        UniVerResponse<HuaYangArea> response = new UniVerResponse<>();
        HuaYangArea huaYangArea = huaYangService.getByUidWithMyBatis(uid);
        response.beTrue(huaYangArea);

        return response;
    }


    /**
     * 根据主键删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/oneHuaYang")
    public UniVerResponse<String> deleteOne(Long id ){
        UniVerResponse.simplCheckField(id,"id");

        UniVerResponse<String> response = new UniVerResponse<>();
        huaYangService.deleteWithMyBatis(id);
        response.beTrue("删除成功");
        return response;
    }

    /**
     * 根据 三个字段 查询
     * 使用mybatis映射文件
     * @param huaYangArea
     * @return
     */
    @GetMapping(value = "/findBy3")
    public UniVerResponse<List<HuaYangModelBean>> findByNameAndPersonAndCreateDate(HuaYangArea huaYangArea){
        UniVerResponse.checkField(huaYangArea,"areaName","areaPerson","createDate");
        UniVerResponse<List<HuaYangModelBean>> response = new UniVerResponse<>();

        try {
            List<HuaYangModelBean> list = huaYangService.findByNameAndPersonAndCreateDateWithMyBatisFile(huaYangArea);
            response.beTrue(list);
        }catch (Exception e){
            throw new MyException("查询错误",UniVerResponse.ERROR_BUSINESS,e);
        }

        return response;
    }

    /**
     * 根据三个字段 映射文件 查询
     *
     * 分页查询
     * @param huaYangArea
     * @return
     */
    @GetMapping(value = "/findBy3Page")
    public PageResponse<HuaYangModelBean> findByNameAndPersonAndCreateDatePage(HuaYangArea huaYangArea){
        UniVerResponse.checkField(huaYangArea,"areaName","areaPerson","createDate","pageNum","pageSize");
        PageResponse<HuaYangModelBean> response = new PageResponse<>();

        try {
            PageInfo<HuaYangModelBean> pageInfo = huaYangService.findByNameAndPersonAndCreateDateWithMyBatisFileAndpagehelper(huaYangArea);
            response.beTrue(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
        }catch (Exception e){
            throw new MyException("查询错误",UniVerResponse.ERROR_BUSINESS,e);
        }

        return response;
    }

}
