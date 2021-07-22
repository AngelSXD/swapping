package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.controller;


import com.alibaba.fastjson.JSON;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.dto.ReportDTO;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.enums.ReportEnum;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.exception.ReportException;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.query.DUQuery;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@Slf4j
@Validated
@RestController
@RequestMapping("/xlsx/downloadAndUpload")
public class XlsxDownloadAndUploadController {


    @Resource
    private ReportService reportService;

    /**
     * 实时下载  xlsx文件
     * 将根据查询条件实时查询到的数据  通过 xlsx文件导出
     * @param query 导出查询条件
     * @return
     */
    @RequestMapping(value = "/rtDownload", method = {RequestMethod.GET})
    public void rtDownload(HttpServletResponse response, @Valid DUQuery query) {
        OutputStream outputStream = null;
        FileInputStream inputStream =null;
        File file = null;
        try {

            ReportDTO dto = new ReportDTO();
            dto.setReportEnum(ReportEnum.RT_REPORT);
            dto.setRequestParams(JSON.toJSONString(query));
            file = reportService.generateReport(dto);



            response.setContentType("mutipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(file.getName(), "utf-8"));
            outputStream = response.getOutputStream();


            inputStream = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, length);
            }
            outputStream.flush();


        } catch (Exception e) {
            log.error("导出业务报表发生错误:", e);
            throw new ReportException("导出业务报表发生错误！");
        }finally{
            reportService.close(outputStream,inputStream);
            reportService.delFile(file);
        }
    }

}
