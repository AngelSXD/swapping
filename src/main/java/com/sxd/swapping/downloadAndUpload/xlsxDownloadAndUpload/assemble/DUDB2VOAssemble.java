package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.assemble;

import com.sxd.swapping.mybatis.pojo.DownloadUpload;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.vo.RTReportVO;
import org.mapstruct.Mapper;

/**
 * mapstruct
 * Spring支持的克隆
 */
@Mapper(componentModel = "spring")
public interface DUDB2VOAssemble {

    RTReportVO from(DownloadUpload downloadUpload);
}
