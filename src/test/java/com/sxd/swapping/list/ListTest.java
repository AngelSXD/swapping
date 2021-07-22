package com.sxd.swapping.list;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.*;



public class ListTest {

    public static void main(String[] args) throws Exception {

        Set<String> set1 = new HashSet<>();
        set1.add("1");
        set1.add("2");


        Set<String> set2 = new HashSet<>();
        set2.add("2");
        set2.add("1");


        Sets.SetView<String> difference = Sets.symmetricDifference(set1, set2);
        System.out.println(difference.size());
        System.out.println("不存在什么"+ Joiner.on(",").join(difference));


    }

}
