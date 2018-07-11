package com.sxd.swapping.test;

import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MyTest {


    @Test
    public  void test1(){
        FileTest fileTest = new FileTest();
        String  aa = fileTest.readToString("D:/新建文本文档.txt");
        List<String> aaList = new ArrayList<>();
        String regex = "@RequestMapping* \\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(aa);
        while (matcher.find()){
            System.out.println(matcher.group());
        }



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
