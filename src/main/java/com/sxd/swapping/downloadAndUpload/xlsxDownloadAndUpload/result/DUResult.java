package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.result;


import lombok.Data;

/**
 * DownloadAndUpload  公共返回对象
 */
@Data
public class DUResult {

    public static final String SUCCESS_CODE = "0000";

    public static final String ERROR_CODE = "9999";

    /**
     * 状态码
     */
    private String code;

    /**
     * 状态码描述
     */
    private String msg;

    /**
     * 响应结果
     */
    private Object data;

    private DUResult(String code, String msg) {
        this(code, msg, null);
    }

    private DUResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static DUResult build(String code, String message) {
        return new DUResult(code, message);
    }

    public static DUResult build(String code, String message, Object data) {
        return new DUResult(code, message, data);
    }

    public static DUResult success() {
        return build(SUCCESS_CODE, "处理成功");
    }

    public static DUResult success(String code, String message) {
        return build(code, message);
    }

    public static DUResult success(Object data) {
        return build(SUCCESS_CODE, "处理成功", data);
    }

    public static DUResult error() {
        return build(ERROR_CODE, "处理失败");
    }

    public static DUResult error(String message) {
        return error(ERROR_CODE, message);
    }

    public static DUResult error(String code, String message) {
        return build(code, message);
    }
}
