package com.sxd.swapping.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 批量bean
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchBean {

    private String likeName;

    private List<Long> areaPersons;
}
