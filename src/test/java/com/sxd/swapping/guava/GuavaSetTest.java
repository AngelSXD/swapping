package com.sxd.swapping.guava;

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
        Set<String> set1 = new HashSet<>();
        set1.add("1");
        set1.add("2");
        set1.add("3");
        set1.add("4");


        Set<String> set2 = new HashSet<>();



        String[] arr2 = new String[1];
        String[] arr = new String[set1.size()];
        arr2 = set1.toArray(arr);

        for (String s : arr2) {
            System.out.println(s);
        }


    }
}
