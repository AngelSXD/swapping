package com.sxd.swapping.service.impl;

import com.sxd.swapping.jpa.dao.GoodsStockDao;
import com.sxd.swapping.mybatis.dao.GoodsStockMapper;
import com.sxd.swapping.jpa.pojo.GoodsStock;
import com.sxd.swapping.service.GoodsStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class GoodsStockServiceImpl implements GoodsStockService {


    @Autowired
    GoodsStockDao dao;

    @Autowired
    GoodsStockMapper mapper;

    /**
     * 数据库加 version 版本号
     *
     * 实现 数据库乐观锁
     *
     * 实现高并发下库存的并发控制机制
     *
     * 要保证事务一致性，要么都使用mybatis  要么都使用jpa
     * @param map
     * @param entity
     * @param threadNum
     * @return
     */
    @Override
    @Transactional
    public void updateStock(Map<Integer,String> map, GoodsStock entity, Integer threadNum) {

        String  uid = entity.getUid();
        Long buyNum = entity.getBuyNum();
        String msg = "";
        //判断库存是否足够
        GoodsStock old = mapper.findByUid(uid);
        Long stock = old.getStock();
        System.out.println("线程"+threadNum+"---------->正在工作");
        if (stock >= buyNum){
            old.setBuyNum(buyNum);
            if (mapper.updateStock(old) > 0 ){
                msg = "库存扣除成功,剩余库存数量:";
            }else {
                msg = "库存扣除失败,剩余库存数量:";
            }
            Long nowStock = mapper.findByUid(uid).getStock();
            msg +=nowStock;
        }else {
            msg = "库存不足,剩余库存数量:"+stock;
        }
        map.put(threadNum,msg);
    }

    @Override
    public GoodsStock save(GoodsStock entity) {
        return dao.save(entity);
    }

    @Override
    public GoodsStock findByUid(String uid) {
        return dao.findByUid(uid);
    }
}

