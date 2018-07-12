package com.sxd.swapping.utils;

public class CodePoolException extends RuntimeException {

    private static final long serialVersionUID = -5049537546441623219L;

    public static final Integer CODE_SIZE = 18;//编码总共 13位数

    public static final Integer ONCE_MAX_SIZE = 8;//一次最多生成千万个码 7位数


    public static final String CODE_VERSION = "1";

    public static final String ERROR_EMPTY_TOTAL = "total 不能为空!";

    public static final String ERROR_TOTAL_IS_TOO_LARGE = "total 太大,不能超过 8位数 最大为 99999999!";


    public CodePoolException(String msg) {
        super(msg);
    }
}
