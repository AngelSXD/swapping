package com.sxd.swapping.mybatis.dao;

import com.sxd.swapping.mybatis.pojo.DownloadUpload;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.query.DUQuery;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface DUMapper {


    List<DownloadUpload> queryDU(DUQuery duQuery);

    int countDU(DUQuery duQuery);
}
