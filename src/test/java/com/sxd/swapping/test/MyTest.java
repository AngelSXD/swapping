package com.sxd.swapping.test;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class MyTest {

    @Test
    public  void test1(){

        User user = new User();
        user.setPassword("adsas");
        user.setUserName("德玛西亚");

        sys(user);


    }

    public void sys(Object object){
        String [] strs = Arrays.stream(object.getClass().getDeclaredFields()).map((it) ->{
            return it.getName();
        }).toArray(String[]::new);
        System.out.println(strs[0]);
    }

    public boolean con(Map<String,String> map,User huaYangArea){
        List<String> properties = Arrays.stream(huaYangArea.getClass().getDeclaredFields()).map(i->i.getName()).collect(Collectors.toList());
        Set<String> keySet = map.keySet();
        if (properties.containsAll(keySet)){
            return true;
        }
        return false;
    }
}
