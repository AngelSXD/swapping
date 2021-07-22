package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.query;

import lombok.Data;

/**
 * 分页查询
 */
@Data
public abstract class PageQuery {

    private Integer currentPage = 1;

    private Integer pageSize = 10;

    private Integer start;



}
