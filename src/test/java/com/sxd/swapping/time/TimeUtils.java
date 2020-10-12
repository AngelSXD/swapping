package com.sxd.swapping.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @Author: SXD
 * @Description:    时间工具类
 * @Date: create in 2020/3/31 13:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeUtils {

    /**
     * 格式1
     */
    final static public String PATTERN_1 = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 当前一天
     * 前一个月的时间
     * 后一个月的时间
     */
    @Test
    public void minuOrPlus(){
        String modifyTime = "20200520103017216";
        Date date = getModifyTime(modifyTime, PATTERN_1);
        System.out.println(date);
    }



    public static Date getModifyTime(String modifyTimeStr, String pattern){
        Date date = null;
        if (modifyTimeStr == null){
            date = new Date();
        }else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern == null ? PATTERN_1 : pattern);
            LocalDateTime localDateTime = LocalDateTime.parse(modifyTimeStr, formatter);
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = localDateTime.atZone(zone).toInstant();
            date = Date.from(instant);
        }
        return date;
    }
}
