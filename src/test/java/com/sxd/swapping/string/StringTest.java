package com.sxd.swapping.string;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

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
    Long  str = 2008101030000001L;

        System.out.println(str.toString().substring(6,9));


    }


}
