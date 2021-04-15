package com.sxd.swapping.mybatis.service;

import com.github.pagehelper.PageInfo;
import com.sxd.swapping.mybatis.dto.HuaYangModelBeanDTO;
import com.sxd.swapping.mybatis.pojo.HuaYangArea;
import com.sxd.swapping.service.ICRUDService;

import java.util.List;
import java.util.Map;


public interface HuaYangService extends ICRUDService<HuaYangArea> {

    int addDDL(String ddl);

    HuaYangArea findByAreaNameLike(String areaName);

    List<HuaYangArea> updates(String areaName,List<HuaYangArea> areas);

    HuaYangArea getByUidWithMyBatis(String uid);

    HuaYangArea insertWithMybatis(HuaYangArea huaYangArea);

    HuaYangArea updateWithMyBatis(HuaYangArea huaYangArea);

    void deleteWithMyBatis(Long id);

    HuaYangArea seleteWithMyBatis(Long id);

    List<HuaYangArea> selectLikeNameWithMyBatis(String areaName);

    List<HuaYangArea> selectLikeNameWithMyBatis2(String areaName);

    List<HuaYangModelBeanDTO> findByNameAndPersonAndCreateDateWithMyBatisFile(HuaYangArea huaYangArea);

    PageInfo<HuaYangModelBeanDTO> findByNameAndPersonAndCreateDateWithMyBatisFileAndpagehelper(HuaYangArea huaYangArea);

    int scheduleUpdate();

    Map<String,String> findMap(HuaYangArea entity);
}
