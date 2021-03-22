package com.sxd.swapping.longs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sxd.swapping.Bean.StuA;
import com.sxd.swapping.Bean.StuB;
import com.sxd.swapping.SwappingApplicationTests;
import com.sxd.swapping.copyUtil.BeanCopyStu;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: SXD
 * @Description:
 * @Date: create in 2019/10/15 21:38
 */

public class LongTest extends SwappingApplicationTests {

    @Test
    public void sortByStream(){
        LocalDateTime start = LocalDateTime.parse("2021-01-10 10:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime end = LocalDateTime.parse("2021-01-27 10:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if(start.isBefore(end.plusDays(-14))){
            System.out.println("促销时间范围不能超过两周");
        }
    }

}
