package com.sxd.swapping.service;

import com.sxd.swapping.jpa.pojo.GoodsStock;
import java.util.Map;

public interface GoodsStockService {

    void updateStock(Map<Integer,String> map, GoodsStock entity, Integer threadNum);

    GoodsStock save(GoodsStock entity);

    GoodsStock findByUid(String uid);

}
