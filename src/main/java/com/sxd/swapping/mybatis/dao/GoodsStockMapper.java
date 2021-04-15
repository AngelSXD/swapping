package com.sxd.swapping.mybatis.dao;

import com.sxd.swapping.jpa.pojo.GoodsStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsStockMapper {

    int updateStock(GoodsStock goodsStock);

    GoodsStock findByUid(@Param("uid") String uid);
}
