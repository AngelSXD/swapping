package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.handler;

import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.dto.ReportDTO;

/**
 * 抽象的报表处理器
 * 各种报表的 自定义的报表处理逻辑 均实现该抽象接口
 *
 */
public abstract class AbstractReportHandler {

    public abstract  void  generateReport(ReportDTO reportDTO);
}
