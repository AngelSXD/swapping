package com.sxd.swapping.service;

import com.sxd.swapping.domain.HuaYangArea;


public interface HuaYangService extends ICRUDService<HuaYangArea>{

    HuaYangArea findByAreaNameLike(String areaName);
}
