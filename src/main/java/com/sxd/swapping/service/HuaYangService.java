package com.sxd.swapping.service;

import com.sxd.swapping.domain.HuaYangArea;

import java.util.List;


public interface HuaYangService extends ICRUDService<HuaYangArea>{

    HuaYangArea findByAreaNameLike(String areaName);

    List<HuaYangArea> updates(String areaName,List<HuaYangArea> areas);

    HuaYangArea getByUid(String uid);
}
