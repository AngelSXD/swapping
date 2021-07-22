package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.exception;

import com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.result.DUResult;

/**
 * 报表自定义业务报警
 */
public class ReportException  extends RuntimeException {

    private final String code;

    public ReportException(String message) {
        super(message);
        this.code = DUResult.ERROR_CODE;
    }

    public ReportException(String code, String message) {
        super(message);
        this.code = code;
    }

}
