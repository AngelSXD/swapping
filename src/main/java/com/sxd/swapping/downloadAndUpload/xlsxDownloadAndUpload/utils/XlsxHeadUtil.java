package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.utils;

import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.enums.ReportEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *  xlsx设置表头工具类
 *
 *  一般情况下 不需要显示设置该表头工具， easeexcel的注解标注了导出表结构一般会正常生成表头，例如RTReportVO类
 *  如果发生导出的xlsx表头不见了，处理该异常则可以通过显式设置该表头 完成xlsx表头的正常生成
 *
 */
public class XlsxHeadUtil {

    public static List<List<String>> getHeadByReportEnum(ReportEnum reportEnum) {
        List<List<String>> heads = new ArrayList<>();
        switch (reportEnum) {
            case RT_REPORT:
                heads = generateRTReportHead();
                break;

            default:
                break;
        }
        return heads;
    }


    private static List<List<String>> generateRTReportHead() {
        List<List<String>>  heads = new ArrayList<>();
        heads.add(Collections.singletonList("业务ID"));
        heads.add(Collections.singletonList("业务名称"));
        heads.add(Collections.singletonList("业务状态"));
        heads.add(Collections.singletonList("业务时间"));

        return heads;
    }



}
