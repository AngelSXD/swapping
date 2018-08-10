package com.sxd.swapping.dao.mybatis;

import com.sxd.swapping.domain.GoodsStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsStockMapper {

    int updateStock(GoodsStock goodsStock);

    GoodsStock findByUid(@Param("uid") String uid);
}
