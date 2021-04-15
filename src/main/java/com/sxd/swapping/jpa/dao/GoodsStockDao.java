package com.sxd.swapping.jpa.dao;

import com.sxd.swapping.jpa.pojo.GoodsStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GoodsStockDao extends JpaRepository<GoodsStock,Long>,JpaSpecificationExecutor<GoodsStock> {


    GoodsStock findByUid(String uid);
}
