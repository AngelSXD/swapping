package com.sxd.swapping.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.*;
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

    final static public String PATTERN_2 = "yyyy-MM-dd";
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

    @Test
    public void between(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_2);
        LocalDate parse = LocalDate.parse("20210511", formatter);

        LocalDate now = LocalDate.now();
        Period between = Period.between(now, parse);
        System.out.println(between.getDays());
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
