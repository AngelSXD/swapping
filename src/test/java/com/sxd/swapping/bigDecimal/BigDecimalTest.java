package com.sxd.swapping.bigDecimal;

import com.google.common.collect.Sets;
import com.sxd.swapping.SwappingApplicationTests;
import org.junit.Test;
import org.paukov.combinatorics3.Generator;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BigDecimalTest extends SwappingApplicationTests {


    @Test
    public void discount(){
        String url = "https://offline-trade-1256468630.cos.ap-beijing.myqcloud.com/proengine-job/test/%E4%BF%83%E9%94%80%E5%95%86%E5%93%81%E6%8A%A5%E8%A1%A8-2020121423115301.xlsx?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDPhojU3JwCLI4e3rLF7QjtvyscAWloJbj%26q-sign-time%3D1607958728%3B1923318728%26q-key-time%3D1607958728%3B1923318728%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3De5811da4917aabbce2cbaad5667426995ae42c6f";
        url = url.replaceFirst("http://","https://");

        System.out.println(url);


    }


    public static <T> List<List<T>> combinationSimple(List<T> list,Integer length){
        return Generator.combination(list)
                .simple(length)
                .stream()
                .collect(Collectors.toList());
    }

    public static <T> List<List<T>> combinationMulti(List<T> list,Integer length){
        return Generator.combination(list)
                .multi(length)
                .stream()
                .collect(Collectors.toList());
    }

}
