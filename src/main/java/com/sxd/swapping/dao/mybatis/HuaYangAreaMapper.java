package com.sxd.swapping.dao.mybatis;

import com.sxd.swapping.base.HuaYangModelBean;
import com.sxd.swapping.domain.HuaYangArea;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;


public interface HuaYangAreaMapper {

    /**
     * 映射文件方法
     * name 模糊查询
     * person >=
     * createDate >=
     * @param huaYangArea
     * @return
     */
    List<HuaYangModelBean> findByNameAndPersonAndCreateDate(HuaYangArea huaYangArea);


    /**
     * 根据业务主键查询
     * @param uid
     * @return
     */
    @Select("SELECT * FROM hua_yang_area where uid = #{uid}")
    @Results({
            @Result(property = "areaName",column = "area_name",javaType = String.class),
            @Result(property = "areaPerson",column = "area_person",javaType = Long.class),
            @Result(property = "createId",column = "create_id",javaType = String.class)
    })
    HuaYangArea findOne(String uid);

    /**
     * 根据数据库主键 查询
     * @param id
     * @return
     */
    @Select("SELECT * FROM hua_yang_area WHERE id = #{id}")
    @Results({
            @Result(property = "areaName",column = "area_name",javaType = String.class),
            @Result(property = "areaPerson",column = "area_person",javaType = Long.class),
            @Result(property = "createId",column = "create_id",javaType = String.class),
            @Result(property = "createDate",column = "create_date",javaType = Date.class),
            @Result(property = "updateDate",column = "update_date",javaType = Date.class)
    })
    HuaYangArea selectById(Long id);

    /**
     * 模糊查询 方法1
     * @param areaName  不传入值  则查到所有
     * @return
     */
    @Select("SELECT * FROM hua_yang_area WHERE area_name like '%${areaName}%'")
    @Results({
            @Result(property = "areaName",column = "area_name",javaType = String.class),
            @Result(property = "areaPerson",column = "area_person",javaType = Long.class),
            @Result(property = "createId",column = "create_id",javaType = String.class),
            @Result(property = "createDate",column = "create_date",javaType = Date.class),
            @Result(property = "updateDate",column = "update_date",javaType = Date.class)
    })
    List<HuaYangArea> selectByNameLike(@Param("areaName") String areaName);

    /**
     * 模糊查询 方法2
     * @param areaName  不传入值则 一条也查不到
     * @return
     */
    @Select("SELECT * FROM hua_yang_area WHERE area_name like CONCAT(CONCAT('%',#{areaName}),'%')")
    @Results({
            @Result(property = "areaName",column = "area_name",javaType = String.class),
            @Result(property = "areaPerson",column = "area_person",javaType = Long.class),
            @Result(property = "createId",column = "create_id",javaType = String.class),
            @Result(property = "createDate",column = "create_date",javaType = Date.class),
            @Result(property = "updateDate",column = "update_date",javaType = Date.class)
    })
    List<HuaYangArea> selectByNameLike2(String areaName);

    /**
     * 插入 新增
     * @param huaYangArea
     * @return
     */
    @Insert("INSERT INTO hua_yang_area(create_date,create_id,uid,area_name,area_person) VALUES (#{createDate},#{createId},#{uid},#{areaName},#{areaPerson}) ")
    void insert(HuaYangArea huaYangArea);

    /**
     * 更新   根据数据库主键更新
     * @param huaYangArea
     */
    @Update("UPDATE hua_yang_area SET update_date=#{updateDate},update_id=#{updateId},area_name=#{areaName},area_person=#{areaPerson} WHERE id=#{id}")
    void update(HuaYangArea huaYangArea);

    /**
     * 删除    根据数据库主键删除
     * @param id
     */
    @Delete("DELETE FROM hua_yang_area WHERE id=#{id}")
    void delete(Long id);



}
