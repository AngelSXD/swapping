package com.sxd.swapping.dao.mybatis;

import com.sxd.swapping.domain.HuaYangArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


public interface HuaYangAreaMapper {

    @Select("SELECT * FROM hua_yang_area where uid = #{uid}")
    @Results({
            @Result(property = "areaName",column = "area_name",javaType = String.class),
            @Result(property = "areaPerson",column = "area_person",javaType = Long.class),
            @Result(property = "createId",column = "create_id",javaType = String.class)
    })
    HuaYangArea findOne(String uid);


}
