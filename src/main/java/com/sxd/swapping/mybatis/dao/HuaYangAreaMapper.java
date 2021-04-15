package com.sxd.swapping.mybatis.dao;

import com.sxd.swapping.mybatis.dto.HuaYangModelBeanDTO;
import com.sxd.swapping.mybatis.pojo.HuaYangArea;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface HuaYangAreaMapper {


    int addRows(@Param("ddl") String ddl);

    /**
     * 映射文件方法
     * name 模糊查询
     * person >=
     * createDate >=
     * @param huaYangArea
     * @return
     */
    List<HuaYangModelBeanDTO> findByNameAndPersonAndCreateDate(HuaYangArea huaYangArea);


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

    /**
     * 单字段更新
     *
     * 根据uid  以及提供的 字段名+字段值  更新单个字段的值
     * @param filed
     * @param content
     * @param uid
     * @return
     */
    @Update("update hua_yang_area set ${filed} = #{context}  where uid = #{uid} ")
    int updateOneFiled(String filed,String content,String uid);

    /**
     * 定时任务调用
     * 给人口数小于10000人的 数据 +1
     * @return
     */
    @Update("update hua_yang_area set area_person = area_person+1 where area_person <10000")
    int scheduleUpdate();

    /**
     * mybatis  查询接收map集合
     * @param area
     * @return
     */
    List<Map<String,String>> findMap(HuaYangArea area);

}
