package com.sxd.swapping.mybatis.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据库表的POJO
 */
@Data
public class DownloadUpload {

    /**
     * DB 自增ID
     */
    private Long id;

    /**
     * 业务ID
     */
    private Long duId;

    /**
     * 业务名称
     */
    private String duName;

    /**
     * 业务状态 枚举值
     */

    private Integer duStatus;


    /**
     * 业务时间
     */
    private LocalDateTime createTime;

}
