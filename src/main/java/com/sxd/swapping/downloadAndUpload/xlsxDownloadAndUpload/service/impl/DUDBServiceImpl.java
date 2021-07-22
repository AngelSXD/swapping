package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.service.impl;

import com.sxd.swapping.mybatis.pojo.DownloadUpload;
import com.sxd.swapping.mybatis.dao.DUMapper;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.query.DUQuery;
import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.service.DUDBService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DUDBServiceImpl implements DUDBService {

    @Resource
    private DUMapper duMapper;

    /**
     * 假设 从第一页开始查询 一直查询到所有数据
     *
     * @param query
     * @return
     */
    @Override
    public List<DownloadUpload> query(DUQuery query) {
        return duMapper.queryDU(query);
    }

    @Override
    public int count(DUQuery duQuery) {
        return duMapper.countDU(duQuery);
    }
}
