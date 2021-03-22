package com.sxd.swapping.list;

import com.alibaba.fastjson.JSON;
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

        List<String> mcList = new ArrayList<>();
        mcList.add("1");
        mcList.add("2");
        mcList.add("3");

        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setId(1);
        student.setName("张三");
        student.setAge(2L);
        student.setMcList(mcList);

        list.add(student);

        Student student2 = new Student();
        student2.setId(1);
        student2.setName("张三1");
        student2.setAge(1L);
        student2.setMcList(mcList);

        list.add(student2);


        Student student3 = new Student();
        student3.setId(3);
        student3.setName("李四");
        student3.setAge(1L);
        student3.setMcList(mcList);

        list.add(student3);

        Student student4 = new Student();
        student4.setId(3);
        student4.setName("李四3");
        student4.setAge(2L);
        student4.setMcList(mcList);

        list.add(student4);

        Map<Integer, List<Student>> map = list.stream().collect(
                Collectors.toMap(
                        Student::getId,
                        stu -> Lists.newArrayList(stu),
                        (List<Student> newList, List<Student> oldList) -> {
                            oldList.addAll(newList);
                            return oldList;
                        }
                )
        );

        System.out.println(JSON.toJSONString(map));

    }



}
