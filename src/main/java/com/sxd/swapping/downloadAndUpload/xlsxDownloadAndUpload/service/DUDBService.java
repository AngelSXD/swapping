package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.service;

import com.sxd.swapping.mybatis.pojo.DownloadUpload;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.query.DUQuery;

import java.util.List;

public interface DUDBService {

    List<DownloadUpload> query(DUQuery query);

    int count(DUQuery duQuery);
}
