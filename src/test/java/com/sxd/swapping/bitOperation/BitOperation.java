package com.sxd.swapping.bitOperation;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.xiaoleilu.hutool.exceptions.ValidateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: SXD
 * @Description:
 * @Date: create in 2019/11/5 14:08
 */
public class BitOperation {

    public static void main(String[] args) {

       try{
           int i = 1/0;
       }catch (Exception e) {
           return;

       }finally {
           System.out.println("finally");
       }

    }

    private static boolean hasDeal(Set<JSONObject> hasDealSet,String wareCode,String proId){
        for (JSONObject jsonObject : hasDealSet) {
            if (wareCode.equals(jsonObject.getString("wareCode")) && proId.equals(jsonObject.getString("proId"))) {
                return true;
            }
        }
        return false;
    }


    private static void addDeal(Set<JSONObject> hasDealSet,String wareCode,String proId) {
        JSONObject dealObj = new JSONObject();
        dealObj.put("wareCode",wareCode);
        dealObj.put("proId",proId);
        hasDealSet.add(dealObj);
    }


    public static void min(Comparable source, Comparable min, String message) {
        if (source.compareTo(min) < 0) {
            System.out.println(message);
        }
    }

    public static LocalDate getLocalDate(String modifyTimeStr, String pattern) {
        LocalDate localDate = null;
        if (modifyTimeStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern == null ? "yyyyMMdd" : pattern);
            localDate = LocalDate.parse(modifyTimeStr, formatter);
        }
        return localDate;
    }


    public static LocalDateTime getLocalTime(String modifyTimeStr, String pattern) {
        LocalDateTime localDateTime = null;
        if (modifyTimeStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern == null ? "yyyy-MM-dd HH:mm:ss" : pattern);
            localDateTime = LocalDateTime.parse(modifyTimeStr, formatter);
        }
        return localDateTime;
    }
}
