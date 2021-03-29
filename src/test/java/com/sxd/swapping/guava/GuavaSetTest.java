package com.sxd.swapping.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: SXD
 * @Description:
 * @Date: create in 2020/1/6 13:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuavaSetTest {

    @Test
    public void setsTest(){

        JSON.toJSONString(null);
        int a = 1;
        try {
            a = a/0;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
