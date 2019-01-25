package com.sxd.swapping.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 计算我们从
 * 2015.06.18 开始的每500天的纪念日
 */
public class OurBirthdayUtil {

    //爱 开始的地方
    final  static LocalDate loveBegin = LocalDate.of(2015,6,18);

    public static void main(String[] args) {
//        如果可以活100个五百天
        int cycle = 100;
        LocalDate everyBirthday = loveBegin;
        for (int i = 1; i <= cycle; i++) {
            everyBirthday = everyBirthday.plus(500, ChronoUnit.DAYS);
            System.out.println("第"+i+"个500天是："+everyBirthday);
        }

    }



}
