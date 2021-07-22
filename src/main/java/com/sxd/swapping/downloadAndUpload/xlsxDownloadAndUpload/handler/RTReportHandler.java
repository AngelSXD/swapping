package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.handler;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.assemble.DUDB2VOAssemble;
import com.sxd.swapping.mybatis.pojo.DownloadUpload;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.dto.ReportDTO;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.enums.DUStatusEnum;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.enums.ReportEnum;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.exception.ReportException;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.query.DUQuery;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.service.DUDBService;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.vo.RTReportVO;
import com.sxd.swapping.utils.dateTime.DateTimeHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 实时报表的 处理器
 * 【每一种报表 是一个单独实现的 处理器】
 * 【所以新增加一种报表  就去单独实现 各自的处理器即可】
 */
@Component
public class RTReportHandler extends AbstractReportHandler{


    @Resource
    private DUDBService dudbService;

    @Resource
    private DUDB2VOAssemble dudb2VOAssemble;


    @Override
    public void generateReport(ReportDTO reportDTO) {
        ExcelWriter excelWriter = reportDTO.getExcelWriter();
        WriteSheet writeSheet = reportDTO.getWriteSheet();

        if (excelWriter == null){
            throw new ReportException("easyexcel写入器不能为空");
        }

        if (writeSheet == null) {
            throw new ReportException("sheet写入器不能为空");
        }

        //1.初始化入参
        String requestParams = reportDTO.getRequestParams();
        ReportEnum reportEnum = reportDTO.getReportEnum();
        DUQuery duQuery = (DUQuery) JSON.parseObject(requestParams, reportEnum.getRequestParams());



        //2.初次查询统计总数
        int total = dudbService.count(duQuery);
        int totalPage = total/duQuery.getPageSize() + 1;



        //3.分页分批次边读边写 【防止超大数据量下FullGC】
        for (int i = 1; i <= totalPage; i++) {
            duQuery.setStart((duQuery.getCurrentPage()-1) * duQuery.getPageSize());
            List<DownloadUpload> dbResult = dudbService.query(duQuery);
            if (CollectionUtils.isNotEmpty(dbResult)) {
                List<RTReportVO> reportDTOs = dbResult.stream().map(dudb2VOAssemble::from).collect(Collectors.toList());
                transferDataDesc(reportDTOs);
                excelWriter.write(reportDTOs,writeSheet);

                clearOneBatch(reportDTOs, dbResult);
            }

        }

    }


    private void clearOneBatch(List<RTReportVO> reportVOS, List<DownloadUpload> dbResult){
        if (reportVOS != null) {
            reportVOS.clear();
        }
        if (dbResult != null ) {
            dbResult.clear();
        }
    }


    /**
     * 将数据转化为描述性文字
     * @param reportVOS
     */
    private void transferDataDesc(List<RTReportVO> reportVOS){

        if (CollectionUtils.isNotEmpty(reportVOS)) {
            reportVOS.forEach(i-> {
                if (i.getDuId() != null) {
                    i.setDuIdDesc(String.valueOf(i.getDuId()));
                }

                if (i.getCreateTime() != null) {
                    i.setCreateTimeDesc(DateTimeHelper.getDateTimeStr(i.getCreateTime()));
                }

                if (i.getDuStatus() != null) {
                    i.setDuStatusDesc(DUStatusEnum.valueOf(i.getDuStatus()).getDesc());
                }
            });
        }

    }
}
