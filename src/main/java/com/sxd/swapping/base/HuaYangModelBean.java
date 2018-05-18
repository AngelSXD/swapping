package com.sxd.swapping.base;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * mybatis 查询返回Model封装
 */
@Getter
@Setter
public class HuaYangModelBean {

    private String uid;

    private Date createDate;

    private Date updateDate;

    private String areaName;

    private Long areaPerson;

}
