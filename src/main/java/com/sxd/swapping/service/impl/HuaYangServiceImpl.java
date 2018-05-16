package com.sxd.swapping.service.impl;

import com.sxd.swapping.dao.HuaYangAreaDao;
import com.sxd.swapping.domain.HuaYangArea;
import com.sxd.swapping.service.HuaYangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class HuaYangServiceImpl implements HuaYangService {

    @Autowired
    HuaYangAreaDao huaYangAreaDao;

    @Override
    public HuaYangArea findByAreaNameLike(String areaName) {
        return huaYangAreaDao.findAllByAreaNameLike(areaName);
    }

    @Override
    @Transactional
    public List<HuaYangArea> updates(String areaName,List<HuaYangArea> areas) {
        huaYangAreaDao.deleteByAreaName(areaName);
        return huaYangAreaDao.saveAll(areas);
    }

    @Override
    public HuaYangArea insert(HuaYangArea entity) throws Exception {
        return huaYangAreaDao.save(entity);
    }

    @Override
    public HuaYangArea update(HuaYangArea entity) throws Exception {
        entity.updateEntity();
        int sign = huaYangAreaDao.update(entity);
        return sign>0 ? entity : null;
    }

    @Override
    public boolean delete(HuaYangArea entity) throws Exception {
        huaYangAreaDao.delete(entity);
        return true;
    }

    @Override
    public Page<HuaYangArea> pageFind(HuaYangArea entity) throws Exception {
        return huaYangAreaDao.findAll(HuaYangArea.where(entity),entity.page());
    }

    @Override
    public Page<HuaYangArea> pageSortFind(HuaYangArea entity, Map<String, Sort.Direction> map) throws Exception {
        return huaYangAreaDao.findAll(HuaYangArea.where(entity),entity.page(map,entity));
    }


    @Override
    public List<HuaYangArea>
    find(HuaYangArea entity) throws Exception {
        return huaYangAreaDao.findAll(HuaYangArea.where(entity));
    }

    @Override
    public List<HuaYangArea> findAll() throws Exception {
        return huaYangAreaDao.findAll();
    }
}
