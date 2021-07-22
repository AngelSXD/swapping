package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.vo;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ColumnWidth(25)
public class RTReportVO {

    /**
     * 业务ID
     */
    @ExcelIgnore
    private Long duId;

    /**
     * 业务ID
     */
    @ExcelProperty(value = "业务ID",index = 0)
    private String duIdDesc;
    /**
     * 业务名称
     */
    @ExcelProperty(value = "业务名称",index = 1)
    private String duName;

    /**
     * 业务状态 枚举值
     */
    @ExcelIgnore
    private Integer duStatus;

    /**
     * 业务状态 枚举描述
     */
    @ExcelProperty(value = "业务状态",index = 2)
    private String duStatusDesc;

    /**
     * 业务时间  DB字段
     */
    @ExcelIgnore
    private LocalDateTime createTime;

    /**
     * 业务时间 展示字段
     */
    @ExcelProperty(value = "业务时间",index = 3)
    private String createTimeDesc;


}
