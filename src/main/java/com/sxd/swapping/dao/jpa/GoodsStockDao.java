package com.sxd.swapping.dao.jpa;

import com.sxd.swapping.domain.GoodsStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GoodsStockDao extends JpaRepository<GoodsStock,Long>,JpaSpecificationExecutor<GoodsStock> {


    GoodsStock findByUid(String uid);
}
