package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.context.ReportGenerateContext;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.dto.ReportDTO;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.enums.ReportEnum;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.exception.ReportException;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.handler.AbstractReportHandler;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.service.ReportService;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.utils.XlsxCellWidthUtil;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.utils.XlsxCellWriteUtil;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.utils.XlsxHeadUtil;
import com.sxd.swapping.utils.serialNum.SerialNumHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 业务报表Service
 *
 * 报表导出的 核心逻辑在这里
 *
 * 1.边读边写 解决FullGC问题
 * 2.集成多种业务报表   不同的报表类型可以在ReportEnum中以不同枚举定义  定义入参class  出参class  报表名称 等关键信息
 * 3.单元格支持超过15位长的纯数字串不会显示成E+问题
 * 4.generateReport()  生成临时File,支持【实时导出】  和 【上传远程服务器得到可直接下载的URL 暂未实现】
 */
@Slf4j
@Service
public class ReportServiceImpl implements ReportService {


    @Resource
    private ReportGenerateContext reportGenerateContext;

    /**
     * 生成报表临时文件
     * @param reportDTO
     * @return
     */
    @Override
    public File generateReport(ReportDTO reportDTO) {
        ReportEnum reportEnum = reportDTO.getReportEnum();
        if (reportEnum == null) {
            throw new ReportException("报表类型必传！");
        }

        if (StringUtils.isBlank(reportDTO.getRequestParams())) {
            throw new ReportException("报表入参数据必传！");
        }



        //1.获取临时文件路径[需定义自定义报表枚举]
        String filePath = getFilePath(reportEnum.getTempFileName());




        //2.初始化xlsx文件对象 [需定义自定义表头工具]
        ExcelWriter excelWriter = EasyExcel
                .write(filePath, reportEnum.getReportClass())
//                .registerWriteHandler(new XlsxCellFontUtil())       //默认情况不用显式设置，除非业务有需求
//                .registerWriteHandler(XlsxCellStyleUtil.getCellStyleStrategy())  //默认情况不用显式设置，除非业务有需求
                .registerWriteHandler(new XlsxCellWidthUtil())      //一般情况下可以不用设置，除非表头没正常生成
                .registerWriteHandler(new XlsxCellWriteUtil())      //如果导出数据中，有超过15位的数字串，则需要设置
                .head(XlsxHeadUtil.getHeadByReportEnum(reportEnum)) //一般情况可以不用设置，除非表头没正常生成
                .build();
        WriteSheet writeSheet = EasyExcel.writerSheet(reportEnum.getDesc()).build();
        reportDTO.setExcelWriter(excelWriter);
        reportDTO.setWriteSheet(writeSheet);



        //3.获取报表handler完成xlsx文件生成[需定义自定义handler并加入上下文]
        AbstractReportHandler reportHandler = reportGenerateContext.getReportHandler(reportEnum);
        reportHandler.generateReport(reportDTO);



        //4.关闭easyxlsx写入器
        excelWriter.finish();


        return new File(filePath);
    }

    /**
     * 根据文件名获取文件路径
     * @param tempFileName
     * @return
     */
    @Override
    public String getFilePath(String tempFileName) {
        Properties properties = System.getProperties();
        String path = properties.getProperty("user.dir");
        if (properties.getProperty("os.name").toLowerCase().contains("win")) {
            path += "\\";
        } else {
            path += "/";
        }
        path += String.format(tempFileName, SerialNumHelper.generateRecordId());

        log.info("DownloadServiceImpl#getFilePath={}" , path);
        return path;
    }

    /**
     * 删除临时文件
     * @param file
     */
    @Override
    public void delFile(File file) {
        if (file != null && file.exists()) {
            try {
                file.delete();
            }catch (Exception e) {
                log.error("删除临时文件失败：", e);
            }
        }
    }

    /**
     * 关闭资源
     * @param outputStream
     * @param inputStream
     */
    @Override
    public void close(OutputStream outputStream, InputStream inputStream) {
        closeOutStream(outputStream);
        cloaseInputStream(inputStream);
    }

    @Override
    public void closeOutStream(OutputStream outputStream) {
        if(outputStream !=null){
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error("关闭输出流失败：", e);
            }
        }
    }

    @Override
    public void cloaseInputStream(InputStream inputStream) {
        if(inputStream != null ){
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("关闭输入流失败：", e);
            }
        }
    }
}
