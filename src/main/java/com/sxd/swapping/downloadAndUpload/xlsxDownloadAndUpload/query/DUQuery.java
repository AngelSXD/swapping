package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 上传下载业务的 查询条件
 * DownloadAndUploadQuery
 */
@Data
public class DUQuery  extends PageQuery{

    /**主键ID*/
//    @NotNull(message = "业务ID不能为空")
    private Long duId;

    /**状态*/
    private Integer duStatus;

}
