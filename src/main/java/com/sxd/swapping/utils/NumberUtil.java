package com.sxd.swapping.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.regex.Pattern;

/**
 * Created by 胡 on 2016/6/22 0022.
 */
public final class NumberUtil {

    private static final String DOT = ".";

    /**
     * 判断是否为有效数值，不能为负数，可以为0
     * 0, 1, 1.2    = true
     * -1, -0.1,-0  = false
     *
     * @param numStr
     * @return
     */
    public static boolean isValidNumInZero(String numStr) {
        return NumberUtils.isNumber(numStr) && !numStr.startsWith("-") ? true : false;
    }

    public static boolean isValidNumInZero(Number number) {
        return (null != number && NumberUtil.isValidNumInZero(number.toString()));
    }

    public static boolean isPositiveNumber(String numStr) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(numStr).matches();
    }

    /**
     * 判断是否有效数值，不能为负数，不可为0
     * 1, 1.2, 1.01     = true
     * 0.0, 00, 0, -0.0    = false
     *
     * @param numStr
     * @return
     */
    public static boolean isValidNumExZero(String numStr) {
        boolean validNumIncludeZero = NumberUtil.isValidNumInZero(numStr);

        boolean notZero = false;

        //判断当前字符串是否非0
        if (validNumIncludeZero) {
            notZero = (0 != Double.parseDouble(numStr)) ? true : false;
        }

        //如果当前字符串为有效数值，并且为非0，返回true
        return validNumIncludeZero && notZero;

    }

    public static boolean isValidNumExZero(Number number) {
        return (null != number && NumberUtil.isValidNumExZero(number.toString()));
    }


    /**
     * 判断数值是否合法
     *
     * @param numStr        待验证数值
     * @param lowNum        不低于值，
     * @param includeLow    是否包含最低值
     * @param afterDotCount 小数点后保留几位
     * @return
     */
    public static boolean isValidNumWithLow(String numStr, Number lowNum, Boolean includeLow, int afterDotCount) {

        if (!NumberUtil.isValidNumInZero(numStr) || !NumberUtil.isValidNumInZero(lowNum)) {
            return false;
        }

        //判断小数点后位数是否合法
        int numAfterDotCount = NumberUtil.fetchAfterDotCount(numStr.toString());
        int dotCount = (afterDotCount < 0) ? 0 : afterDotCount;

        //对比小数点后数值长度，如果num > lowNum则为非法数值
        if (dotCount < numAfterDotCount) {
            return false;
        }

        //判断数值大小是否合法
        double num = Double.parseDouble(numStr);
        double lowNumD = lowNum.doubleValue();

        if (null == includeLow || true == includeLow) {
            return num >= lowNumD;
        } else {
            return num > lowNumD;
        }

    }

    /**
     * @param numStr
     * @param highNum
     * @param includeHigh
     * @param afterDotCount 小数点后保留几位
     * @return
     */
    public static boolean isValidNumWithHigh(String numStr, Number highNum, Boolean includeHigh, int afterDotCount) {

        if (!NumberUtil.isValidNumInZero(numStr) || !NumberUtil.isValidNumInZero(highNum)) {
            return false;
        }

        //判断小数点后位数是否合法
        int numAfterDotCount = NumberUtil.fetchAfterDotCount(numStr.toString());
        int dotCount = (afterDotCount < 0) ? 0 : afterDotCount;

        //对比小数点后数值长度，如果num > lowNum则为非法数值
        if (dotCount < numAfterDotCount) {
            return false;
        }

        //判断数值大小是否合法
        double num = Double.parseDouble(numStr);
        double highNumD = highNum.doubleValue();

        if (null == includeHigh || true == includeHigh) {
            return num <= highNumD;
        } else {
            return num < highNumD;
        }

    }

    /**
     * @param numStr
     * @param lowNum
     * @param includeLow
     * @param highNum
     * @param includeHigh
     * @param afterDotCount 小数点后保留几位
     * @return
     */
    public static boolean isValidNumInRange(String numStr, Number lowNum, Boolean includeLow, Number highNum, Boolean includeHigh, int afterDotCount) {
        if (!NumberUtil.isValidNumInZero(numStr) || !NumberUtil.isValidNumInZero(lowNum) || !NumberUtil.isValidNumInZero(highNum)) {
            return false;
        }

        //判断小数点后位数是否合法
        int numAfterDotCount = NumberUtil.fetchAfterDotCount(numStr.toString());
        int dotCount = (afterDotCount < 0) ? 0 : afterDotCount;

        //对比小数点后数值长度，如果num > lowNum则为非法数值
        if (dotCount < numAfterDotCount) {
            return false;
        }

        //判断数值大小是否合法
        double num = Double.parseDouble(numStr);
        double highNumD = highNum.doubleValue();
        double lowNumD = lowNum.doubleValue();

        if ((null != includeHigh) && (null != includeLow) && (true == includeHigh) && (true == includeLow) ) {   //包含最高，包含最低
            return ((num >= lowNumD) && (num <= highNumD));
        } else if ((null != includeHigh) && (null != includeLow) && (false == includeHigh) && (true == includeLow) ) {  //不包含最高，包含最低
            return ((num >= lowNumD) && (num < highNumD));
        } else if ((null != includeHigh) && (null != includeLow) && (true == includeHigh) && (false == includeLow) ) {  //包含最高，不包含最低
            return ((num > lowNumD) && (num <= highNumD));
        } else if ((null != includeHigh) && (null != includeLow) && (false == includeHigh) && (false == includeLow)) {  //不包含最高，不包含最低
            return ((num > lowNumD) && (num < highNumD));
        }

        return false;

    }

    /**
     * 判断数值是否合法
     *
     * @param num           待验证数值
     * @param lowNum        不能低于值，
     * @param includeLow    是否包含最低值
     * @param afterDotCount 小数点后保留几位
     * @return
     */
    public static boolean isValidNumWithLow(Number num, Number lowNum, Boolean includeLow, int afterDotCount) {
        if (null == num) {
            return false;
        }
        return NumberUtil.isValidNumWithLow(num.toString(), lowNum, includeLow, afterDotCount);
    }

    /**
     * 判断数值是否合法
     *
     * @param num           待验证数值
     * @param highNum       不高于
     * @param includeHigh   是否包含最高值
     * @param afterDotCount 小数点后保留几位
     * @return
     */
    public static boolean isValidNumWithHigh(Number num, Number highNum, Boolean includeHigh, int afterDotCount) {
        if (null == num) {
            return false;
        }

        return NumberUtil.isValidNumWithHigh(num.toString(), highNum, includeHigh, afterDotCount);
    }

    /**
     * @param num
     * @param lowNum
     * @param includeLow
     * @param highNum
     * @param includeHigh
     * @param afterDotCount 小数点后保留几位
     * @return
     */
    public static boolean isValidNumInRange(Number num, Number lowNum, Boolean includeLow, Number highNum, Boolean includeHigh, int afterDotCount) {
        if (null == num) {
            return false;
        }
        return NumberUtil.isValidNumInRange(num.toString(), lowNum, includeLow, highNum, includeHigh, afterDotCount);
    }


    /**
     * 获取小数点后的数值长度
     *
     * @param numStr
     * @return
     */
    private static int fetchAfterDotCount(String numStr) {

        int dotCount = 0;

        //如果不包含小数点，则直接返回0
        if (!numStr.contains(NumberUtil.DOT)) {
            return dotCount;
        }

        //将数值分组
        String[] numArr = numStr.split("\\" + NumberUtil.DOT);

        //分组有效，则返回小数点后数值数量
        if ((numArr.length == 2) && !StringUtils.isEmpty(numArr[1])) {
            dotCount = numArr[1].length();
        }

        return dotCount;
    }

}
