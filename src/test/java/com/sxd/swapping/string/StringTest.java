package com.sxd.swapping.string;



import com.alibaba.fastjson.JSON;
import com.sxd.swapping.utils.arithmetic.SortUtil;

/**
 * @Author: SXD
 * @Description: 字符串 测试工具类
 * @Date: create in 2019/10/9 15:14
 */
public class StringTest {



    public static void main(String[] args) {
        int [] arr = new int[5];
        arr[0] = 5;
        arr[1] = 1;
        arr[2] = 3;
        arr[3] = 0;
        arr[4] = 8;

        System.out.println(JSON.toJSONString(arr));
        SortUtil.mergeSortArr(arr, 5);

        System.out.println(JSON.toJSONString(arr));


    }


}
