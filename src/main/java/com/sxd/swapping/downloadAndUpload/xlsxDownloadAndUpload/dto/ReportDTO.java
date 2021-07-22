package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.dto;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.enums.ReportEnum;
import lombok.Data;

/**
 * 导出报表的 过程数据
 */
@Data
public class ReportDTO {

    /**报告类型枚举*/
    private ReportEnum reportEnum;

    /**序列化入参*/
    private String requestParams;

    /**easyexcel写入器  所有报表通用*/
    private ExcelWriter excelWriter;

    /**sheet写入器   所有报表通用*/
    private WriteSheet writeSheet;

}
