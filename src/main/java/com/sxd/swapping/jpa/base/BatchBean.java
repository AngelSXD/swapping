package com.sxd.swapping.jpa.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 批量bean
 *
 * 用于前台传递API接收请求数据
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchBean {

    private String likeName;

    private List<Long> areaPersons;
}
