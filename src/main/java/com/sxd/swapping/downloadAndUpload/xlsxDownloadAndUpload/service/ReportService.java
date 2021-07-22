package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.service;

import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.dto.ReportDTO;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 业务报表Service
 */
public interface ReportService {

    /**生成报告 临时文件*/
    File generateReport(ReportDTO reportDTO);

    /**获取临时文件路径*/
    String getFilePath(String tempFileName);

    /**清除临时文件*/
    void delFile(File file);



    /**关闭资源*/
    void close(OutputStream outputStream, InputStream inputStream);

    /**关闭输出流*/
    void closeOutStream(OutputStream outputStream);

    /**关闭输入流*/
    void cloaseInputStream(InputStream inputStream);

}
