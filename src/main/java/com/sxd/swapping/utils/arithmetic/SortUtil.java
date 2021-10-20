package com.sxd.swapping.utils.arithmetic;

import com.alibaba.fastjson.JSON;

/**
 * 冒泡算法
 * @author SXD
 * @date 2021.10.12
 */
public class SortUtil {

    private static Integer num = 1;
    /**
     *  冒泡排序
     * 每次循环按照大小关系要求 对比相邻的两个数据，如果不满足，就互换。
     * 一次循环会让至少一个元素移动到它应该在的位置。
     * 最多循环n-1次，能完成N个数据的排序工作。
     *
     * @param arr   数组
     * @param desc  是否倒序 默认false=升序 true=倒序
     */
    public static void bubbleSortArr(int [] arr, boolean desc){
        if (arr == null) return;
        int arrLength = arr.length;

        for (int i = 0; i < arrLength; i++) {//外层至少循环n-1次
            boolean flag = false;
            for (int j = 0; j < arrLength-i-1; j++) {//内层每个相邻的数据做对比  已经冒过泡的则无需再比较，因此内层循环次数为arrLength-i-1
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

    /**
     * 插入算法
     * 将数据分为 已排序区间 和 未排序区间
     * 初始已排序区间 只有数组第一个元素。
     *
     * 核心思想是 取未排序区间中的元素，在已排序区间中找到合适位置并插入，并保证已排序区间完全有序。
     * 重复该动作，直到未排序区间为空。
     *
     * @param arr   数组
     * @param desc  是否倒序 true=倒序  false=升序
     */
    public static void insertSortArr(int [] arr, boolean desc) {
        if (arr == null) return;
        int arrLength = arr.length;

        for (int i = 1; i < arrLength; i++) {//一开始的已排序区间只有数组第一个元素，因此从第二个开始循环
            int waitInsert = arr[i];
            int j = i - 1;
            for ( ; j >= 0 ; j--) {
                if (desc ? arr[j] < waitInsert : arr[j] > waitInsert) {
                    arr[j+1] = arr[j]; //j+1位置为什么直接能放前一个的值，值不怕丢么？不怕，因为j = i-1值已经被waitInsert记录了
                }else {
                    break;  //一旦发现了正确的位置，就不用在已排序的区间继续找下去了，发现的位置就是待带入的正确位置了
                }
            }
            arr[j+1] = waitInsert;
        }
    }



    public static void mergeSortArr(int [] a, int n) {
        mergeSortInternally(a, 0, n-1);
    }

    private static void mergeSortInternally(int[] a, int p, int r) {
        if (p >= r) return; //递归终止

        int q = p + (r - p)/2;
        //分治
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, q + 1, r);
        //合并
        merge(a, p, q, r);
    }

    private static void merge(int [] a, int p, int q, int r){
        int i = p; int j = q+1; int k = 0;

        int[] temp = new int[r-p+1];
        while (i<=q && j<=r) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            }else {
                temp[k++] = a[j++];
            }
        }

        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        while (start <= end) temp[k++] = a[start++];

        for (i = 0; i <= r-p; ++i) a[p+i] = temp[i];
    }


//    private static void merge(int [] arr, int start, int middle, int end ) {
//
//        //准备对比的变量
//        int firstIndex = start; //前半部分的开始index
//        int lastIndex = middle+1;//后半部分的开始index
//
//        int [] temp = new int[end - start + 1]; //申请临时变量数组
//        int tempIndex = 0;
//
//        //当前半部分还没有走完 && 后半部分还没走完 就能一直 两部分取数对比
//        while (firstIndex <= middle && lastIndex <= end) {
//            //如果是倒序
//            if (desc) {
//                //则当前半部分取出来的数 比 后半部分的数小，那就把后半部分的这个数放入临时数组中
//                if (arr[firstIndex] <= arr[lastIndex]) {
//                    temp[tempIndex++] = arr[lastIndex++];
//                }else {
//                    temp[tempIndex++] = arr[firstIndex++];
//                }
//            }else {
//                if (arr[firstIndex] <= arr[lastIndex]) {
//                    temp[tempIndex++] = arr[firstIndex++];
//                }else {
//                    temp[tempIndex++] = arr[lastIndex++];
//                }
//            }
//        }
//
//        //先默认剩余的数组是前半部分
//        int s = firstIndex;
//        int e = middle;
//        //如果发现是后半部分有剩余，就重新定义范围
//        if (lastIndex <= end) {
//            s = lastIndex;
//            e = end;
//        }
//
//        //将剩余的数组放入临时数组中
//        while (s <= e) {
//            temp[tempIndex++] = arr[s++];
//        }
//
//
//        //将临时数组变量放入原数组中
//        for (int i = 0; i < end-start; i++) {
//            arr[start+i] = temp[i];
//        }
//
//    }


    /**
     * 快速排序
     * @param a
     * @param n
     */
    public static  void quickSortArr(int [] a, int n){
        quickSortInternally(a,0,n-1);
    }

    private static void quickSortInternally(int [] a, int p, int r){
        if (p >= r) return;

        int q = partation(a,p,r); //分区
        quickSortInternally(a,p,q-1);
        quickSortInternally(a,q+1,r);

    }

    private static int partation(int [] a, int p, int r){
        int pivot = a[r]; //选择分区点
        int i = p; //记录分取位置
        for (int j = p; j < r; j++) {
            if (a[j] < pivot) { //小于分区点，替换至左侧位置
                if (i== j) {
                    ++i;
                }else {
                    int temp = a[i];
                    a[i++] = a[j];
                    a[j] = temp;
                }
            }
        }


        //最后将分区点元素放在分区位置
        int temp = a[i];
        a[i] = a[r];
        a[r] = temp;

        return i;
    }

}
