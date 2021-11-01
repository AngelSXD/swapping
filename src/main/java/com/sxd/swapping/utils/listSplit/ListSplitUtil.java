package com.sxd.swapping.utils.listSplit;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SXD
 * @Description:    集合切割Util  支持按份数切割/按目标容量切割
 * @Date: create in 2020/3/24 16:56
 */
public class ListSplitUtil {
    /**
     * 1> 按照份数---划分list
     * @param source
     * @param num    想要划分成多少份
     * @return
     */
    public static <T> List<List<T>> splitListForNum(List<T> source, int num){
        List<List<T>> result=new ArrayList<List<T>>();
        int remaider=source.size()%num;  //(先计算出余数)
        int number=source.size()/num;  //然后是商
        int offset=0;//偏移量
        for(int i=0;i<num;i++){
            List<T> value=null;
            if(remaider>0){
                value=source.subList(i*number+offset, (i+1)*number+offset+1);
                remaider--;
                offset++;
            }else{
                value=source.subList(i*number+offset, (i+1)*number+offset);
            }
            result.add(value);
        }
        return result;
    }

    /**
     * 2> 根据目标容量 划分List
     * @param source
     * @param capacity 划分完成的单个List容量
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitListBycapacity(List<T> source,int capacity){
        List<List<T>> result=new ArrayList<List<T>>();
        if (source != null){
            int size = source.size();
            if (size > 0 ){
                for (int i = 0; i < size;) {
                    List<T> value = null;
                    int end = i+capacity;
                    if (end > size){
                        end = size;
                    }
                    value = source.subList(i,end);
                    i = end;

                    result.add(value);
                }

            }else {
                result = null;
            }
        }else {
            result = null;
        }


        return result;
    }
}
