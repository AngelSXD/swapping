package com.sxd.swapping.mybatis.controller;

import com.github.pagehelper.PageInfo;
import com.sxd.swapping.WorkSheetUtils.DDLCreater;
import com.sxd.swapping.mybatis.dto.HuaYangModelBeanDTO;
import com.sxd.swapping.mybatis.vo.PageResponse;
import com.sxd.swapping.mybatis.vo.UniVerResponse;
import com.sxd.swapping.mybatis.pojo.HuaYangArea;
import com.sxd.swapping.mybatis.service.HuaYangService;
import com.sxd.swapping.mybatis.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/huayangMybatis")
public class HuaYangApiWithMyBatis {

    @Autowired
    HuaYangService huaYangService;


    @RequestMapping(value = "/addDdl", method = RequestMethod.GET)
    public UniVerResponse<String> addDdl(){
        UniVerResponse<String> res = new UniVerResponse<>();
        DDLCreater creater = new DDLCreater(12L);

        List<String> list = new ArrayList<>();
        list.add("input_0");
        list.add("textarea_0");
        list.add("area_0");
        for (String s : list) {
            creater.addField(s);
        }
        creater.addField("phone_0",true);
        String ddl = creater.getAddDDL();
        System.out.println(ddl);

        huaYangService.addDDL(ddl);

        res.beTrue("成功");

        return res;
    }

    @PostMapping("/insert")
    public UniVerResponse<HuaYangArea> insert(@RequestBody HuaYangArea huaYangArea){
        UniVerResponse.checkField(huaYangArea,"areaName","areaPerson");
        huaYangArea.initEntity();
        System.out.println("mybatis测试java初始化时间："+ huaYangArea.getCreateDate());
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
    public UniVerResponse<List<HuaYangModelBeanDTO>> findByNameAndPersonAndCreateDate(HuaYangArea huaYangArea){
        UniVerResponse.checkField(huaYangArea,"areaName","areaPerson","createDate");
        UniVerResponse<List<HuaYangModelBeanDTO>> response = new UniVerResponse<>();

        try {
            List<HuaYangModelBeanDTO> list = huaYangService.findByNameAndPersonAndCreateDateWithMyBatisFile(huaYangArea);
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
    public PageResponse<HuaYangModelBeanDTO> findByNameAndPersonAndCreateDatePage(HuaYangArea huaYangArea){
        UniVerResponse.checkField(huaYangArea,"areaName","areaPerson","createDate","pageNum","pageSize");
        PageResponse<HuaYangModelBeanDTO> response = new PageResponse<>();

        try {
            PageInfo<HuaYangModelBeanDTO> pageInfo = huaYangService.findByNameAndPersonAndCreateDateWithMyBatisFileAndpagehelper(huaYangArea);
            response.beTrue(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
        }catch (Exception e){
            throw new MyException("查询错误",UniVerResponse.ERROR_BUSINESS,e);
        }

        return response;
    }

    @GetMapping(value = "/findMap")
    public UniVerResponse<Map<String,String>> findMap(HuaYangArea entity){
        UniVerResponse<Map<String,String>> res = new UniVerResponse<>();
        res.beTrue(huaYangService.findMap(entity));

        return res;
    }

}
