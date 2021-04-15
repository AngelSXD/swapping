package com.sxd.swapping.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxd.swapping.mybatis.dto.HuaYangModelBeanDTO;
import com.sxd.swapping.jpa.dao.HuaYangAreaDao;
import com.sxd.swapping.mybatis.dao.HuaYangAreaMapper;
import com.sxd.swapping.mybatis.pojo.HuaYangArea;
import com.sxd.swapping.mybatis.service.HuaYangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HuaYangServiceImpl implements HuaYangService {

    @Autowired
    HuaYangAreaDao huaYangAreaDao;

    @Autowired
    HuaYangAreaMapper huaYangAreaMapper;


    @Override
    public int addDDL(String ddl) {
        return huaYangAreaMapper.addRows(ddl);
    }

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

//        注意查看page2()不被推荐使用的原因
//        return  huaYangAreaDao.findAll(HuaYangArea.where(entity),entity.page2());
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

    @Override
    public HuaYangArea getByUidWithMyBatis(String uid) {
        return huaYangAreaMapper.findOne(uid);
    }

    @Override
    public HuaYangArea insertWithMybatis(HuaYangArea huaYangArea) {
        huaYangAreaMapper.insert(huaYangArea);
        return huaYangAreaMapper.findOne(huaYangArea.getUid());
    }

    @Override
    public HuaYangArea updateWithMyBatis(HuaYangArea huaYangArea) {
        huaYangAreaMapper.update(huaYangArea);
        return huaYangAreaMapper.selectById(huaYangArea.getId());
    }

    @Override
    public void deleteWithMyBatis(Long id) {
        huaYangAreaMapper.delete(id);
    }

    @Override
    public HuaYangArea seleteWithMyBatis(Long id) {
        return huaYangAreaMapper.selectById(id);
    }

    @Override
    public List<HuaYangArea> selectLikeNameWithMyBatis(String areaName) {
        return huaYangAreaMapper.selectByNameLike(areaName);
    }

    @Override
    public List<HuaYangArea> selectLikeNameWithMyBatis2(String areaName) {
        return huaYangAreaMapper.selectByNameLike2(areaName);
    }

    @Override
    public List<HuaYangModelBeanDTO> findByNameAndPersonAndCreateDateWithMyBatisFile(HuaYangArea huaYangArea) {
        return huaYangAreaMapper.findByNameAndPersonAndCreateDate(huaYangArea);
    }

    @Override
    public PageInfo<HuaYangModelBeanDTO> findByNameAndPersonAndCreateDateWithMyBatisFileAndpagehelper(HuaYangArea huaYangArea) {
        PageHelper.startPage(huaYangArea.getPageNum(),huaYangArea.getPageSize());
        List<HuaYangModelBeanDTO> list = huaYangAreaMapper.findByNameAndPersonAndCreateDate(huaYangArea);
        PageInfo<HuaYangModelBeanDTO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int scheduleUpdate() {
        return huaYangAreaMapper.scheduleUpdate();
    }

    @Override
    public Map<String, String> findMap(HuaYangArea entity) {
        List<Map<String,String>> list = huaYangAreaMapper.findMap(entity);
        Map<String,String> res = new HashMap<>();
        for (Map<String, String> stringStringMap : list) {
            System.out.println(stringStringMap.toString());

            String uid = null;
            String areaName = null;

            for (Map.Entry<String,String> entry:stringStringMap.entrySet()){
                if ("uid".equals(entry.getKey())){
                    uid = entry.getValue();
                }else {
                    areaName = entry.getValue();
                }
            }

            res.put(uid,areaName);
        }
        return res;
    }

}
