package com.sxd.swapping.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.sxd.swapping.Bean.StuA;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @Author: SXD
 * @Description:
 * @Date: create in 2019/10/12 13:34
 */
public class ListTest {

    public static void main(String[] args) throws Exception {

        String a = "{\"pageInfoConfig\":{\"baseInfoModule\":{\"proCode\":false},\"ruleInfoModule\":{\"thridPlatform\":[2,3]},\"goodsInfoModule\":{\"skuType\":[]}},\"businessConfig\":{\"reconfirm\":true,\"autoAudit\":false,\"actType\":[1]}}";
        JSONObject jsonObject = JSONObject.parseObject(a);


        System.out.println(jsonObject);

    }






}
