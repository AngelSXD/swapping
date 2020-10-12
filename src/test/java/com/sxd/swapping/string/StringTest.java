package com.sxd.swapping.string;



import java.text.MessageFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: SXD
 * @Description: 字符串 测试工具类
 * @Date: create in 2019/10/9 15:14
 */
public class StringTest {



    public static void main(String[] args) {
String  str = "pro:asyn:export:record2:12123123:1:12312312312312";
        String[] split = str.split(":");
        System.out.println(split[split.length-3]);
    }


}
