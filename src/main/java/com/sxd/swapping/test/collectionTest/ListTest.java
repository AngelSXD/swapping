package com.sxd.swapping.test.collectionTest;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author sxd
 * @date 2019/5/15 16:42
 */
public class ListTest {

    public static void main(String[] args) {
        String cname = "{\"input_0\":\"222子表单输入框改过自新\"}";
        JSONObject jsonObject = JSONObject.parseObject(cname);
        System.out.println(jsonObject);

        Set<String> strings = jsonObject.keySet();
        for (String s : strings) {
            System.out.println(s);
        }




    }




}
