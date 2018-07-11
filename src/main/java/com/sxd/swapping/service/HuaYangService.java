package com.sxd.swapping.service;

import com.github.pagehelper.PageInfo;
import com.sxd.swapping.base.HuaYangModelBean;
import com.sxd.swapping.domain.HuaYangArea;

import java.util.List;


public interface HuaYangService extends ICRUDService<HuaYangArea>{

    HuaYangArea findByAreaNameLike(String areaName);

    List<HuaYangArea> updates(String areaName,List<HuaYangArea> areas);

    HuaYangArea getByUidWithMyBatis(String uid);

    HuaYangArea insertWithMybatis(HuaYangArea huaYangArea);

    HuaYangArea updateWithMyBatis(HuaYangArea huaYangArea);

    void deleteWithMyBatis(Long id);

    HuaYangArea seleteWithMyBatis(Long id);

    List<HuaYangArea> selectLikeNameWithMyBatis(String areaName);

    List<HuaYangArea> selectLikeNameWithMyBatis2(String areaName);

    List<HuaYangModelBean> findByNameAndPersonAndCreateDateWithMyBatisFile(HuaYangArea huaYangArea);

    PageInfo<HuaYangModelBean> findByNameAndPersonAndCreateDateWithMyBatisFileAndpagehelper(HuaYangArea huaYangArea);

    int scheduleUpdate();
}
