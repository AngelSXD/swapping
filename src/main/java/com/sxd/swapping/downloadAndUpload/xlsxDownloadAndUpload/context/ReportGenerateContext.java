package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.context;

import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.enums.ReportEnum;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.handler.AbstractReportHandler;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.handler.RTReportHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 报表生成上下文
 */
@Component
public class ReportGenerateContext implements ApplicationContextAware {

    private Map<ReportEnum, AbstractReportHandler> reportHandlerMap;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (reportHandlerMap == null) {
            reportHandlerMap = new HashMap<>();
            reportHandlerMap.put(ReportEnum.RT_REPORT,applicationContext.getBean(RTReportHandler.class));
        }
    }


    public AbstractReportHandler getReportHandler(ReportEnum reportEnum){
        return reportHandlerMap.get(reportEnum);
    }
}
