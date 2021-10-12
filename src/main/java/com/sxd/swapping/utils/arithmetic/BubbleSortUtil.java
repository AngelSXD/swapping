package com.sxd.swapping.utils.arithmetic;

/**
 * 冒泡算法
 * @author xudong.shen
 * @date 2021.10.12
 */
public class BubbleSortUtil {


    /**
     *
     * @param arr   数组
     * @param desc  是否倒序 默认false=升序 true=倒序
     */
    public static void bubbleSortArr(int [] arr, boolean desc){
        if (arr == null) return;
        int arrLength = arr.length;

        for (int i = 0; i < arrLength; i++) {//外层至少循环n-1次
            boolean flag = false;
            for (int j = 0; j < arrLength-i-1; j++) {//内层每个相邻的数据做对比
                if (desc ? arr[j] < arr[j+1] : arr[j] > arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;

                    flag = true;
                }
            }
            if (!flag) break; //没有数据交换 则退出外层本次循环
        }
    }
}
