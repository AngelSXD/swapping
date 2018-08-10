package com.sxd.swapping.domain;

import com.sxd.swapping.base.BaseBean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 低配版本的 商品库存表
 */
@Entity
@Table
@Getter
@Setter
public class GoodsStock  extends BaseBean {

    private String goodsName;//商品名称

    private String goodsPrice;//商品价格

    private Long buyNum;//购买数量

    private Long saleNum;//销售量

    private Long stock;//商品库存       库存为-1  代表无限量库存

    private Integer version;//版本号

    @Transient
    private Integer threadCount;//模拟并发访问的线程数量 实际业务中不用这个字段  仅用作本次测试接口使用

}
