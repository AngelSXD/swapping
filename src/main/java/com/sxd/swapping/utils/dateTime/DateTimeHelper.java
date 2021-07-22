package com.sxd.swapping.utils.dateTime;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {

    public static final String PATTERN_1 = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_2 = "yyyyMMddHHmmssSSS";
    /**
     *  时间类型 转  字符串
     * @param localDateTime
     * @return
     */
    public static String  getDateTimeStr(LocalDateTime localDateTime){
        if (localDateTime == null) {
            return null;
        }

        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(PATTERN_1);
            return localDateTime.format(format);
        } catch (DateTimeException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
