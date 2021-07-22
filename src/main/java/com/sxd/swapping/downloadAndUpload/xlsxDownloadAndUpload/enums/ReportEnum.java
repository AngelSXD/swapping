package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.query.DUQuery;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.vo.RTReportVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum  ReportEnum {

    /** 枚举项 */
    RT_REPORT(1, "实时导出报表", RTReportVO.class, DUQuery.class,"实时导出报表-%s.xlsx"),

    ;

    /**值*/
    private Integer value;

    /**报表描述*/
    private String desc;

    /**导出模板*/
    private Class reportClass;

    /**业务入参*/
    private Class requestParams;

    /**报表文件名称*/
    private String tempFileName;



    @JsonCreator
    public static ReportEnum valueOf(Integer value) {
        return Arrays.stream(ReportEnum.values())
                .filter(e -> Objects.equals(e.value, value)).findFirst()
                .orElseThrow(() -> new RuntimeException("ReportEnum value=" + value + " not exists!"));
    }
}
