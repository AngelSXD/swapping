package com.sxd.swapping.dao.jpa;

import com.sxd.swapping.domain.HuaYangArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface HuaYangAreaDao  extends JpaRepository<HuaYangArea,Long> ,JpaSpecificationExecutor<HuaYangArea>{

    /**
     * 简单JPA操作 映射方法名 查询操作  根据areaName模糊查询
     * 查询操作 不需要@Modifying注解支持
     * @param areaName  字段-区域名称
     * @return
     */
    HuaYangArea findAllByAreaNameLike(String areaName);

    /**
     *复杂JPA操作  使用@Query()自定义sql语句  根据业务id UId去更新整个实体
     * 删除和更新操作，需要@Modifying和@Transactional注解的支持
     *
     * 更新操作中 如果某个字段为null则不更新，否则更新【注意符号和空格位置】
     *
     * @param huaYangArea   传入实体，分别取实体字段进行set
     * @return  更新操作返回sql作用条数
     */
    @Modifying
    @Transactional
    @Query("update HuaYangArea hy set " +
            "hy.areaName = CASE WHEN :#{#huaYangArea.areaName} IS NULL THEN hy.areaName ELSE :#{#huaYangArea.areaName} END ," +
            "hy.areaPerson = CASE WHEN :#{#huaYangArea.areaPerson} IS NULL THEN hy.areaPerson ELSE :#{#huaYangArea.areaPerson} END ," +
            "hy.updateDate = CASE WHEN :#{#huaYangArea.updateDate} IS NULL THEN hy.updateDate ELSE :#{#huaYangArea.updateDate} END ," +
            "hy.updateId =  CASE WHEN :#{#huaYangArea.updateId} IS NULL THEN hy.updateId ELSE :#{#huaYangArea.updateId} END " +
            "where hy.uid = :#{#huaYangArea.uid}")
    int update(@Param("huaYangArea") HuaYangArea huaYangArea);

    /**
     * 复杂JPA操作  使用@Query()自定义sql语句 更新部分字段
     * @param areaPerson    字段--区域人数
     * @param uId           字段--业务ID
     * @return              更新操作返回sql作用条数
     */
    @Modifying
    @Transactional
    @Query("update HuaYangArea hy set " +
            "hy.areaPerson = :areaPerson where hy.uid =:uId")
    int update(@Param("areaPerson")Long areaPerson,@Param("uId") String uId);

    @Modifying
    @Transactional
    @Query("delete from HuaYangArea hy where hy.areaName like "+"%"+":areaName"+"%")
    void deleteByAreaName(@Param("areaName") String areaName);


}
