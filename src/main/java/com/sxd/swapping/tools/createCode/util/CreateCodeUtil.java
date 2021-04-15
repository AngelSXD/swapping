package com.sxd.swapping.tools.createCode.util;


import com.sxd.swapping.tools.createCode.exception.CodePoolException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 生码工具类
 */
public class CreateCodeUtil {

    /**
     * 伪随机数 类
     */
    private static final Random RANDOM = new Random();



    /**
     * 方法1  各种类型前补位拼接方法
     * @param obj   传入原始对象 可以是任意类型[123]
     * @param length    拼接结束的字符串总长度[10]
     * @param appendString  用来拼接的元素["0"]
     * @return eq:0000000123
     * 德邦德邦德邦德邦德邦德邦德邦德邦德邦德邦德邦德邦德邦德邦德邦德邦com.sxd.swapping.domain.HuaYangArea@49c2faae
     */
    public static String fmtStringAddZero(Object obj,int length,String appendString){

        String string = obj.toString();

        length = length - string.length();

        String res = "";

        if(length > 0){
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                sb.append(appendString);
            }
            sb.append(string);
            res = sb.toString();
        }else{
            res = string;
        }
        return res;

    }

    /**
     *生成指定长度的随机数
     * @param realitySize
     * @return
     */
    private static String getRandomString(Integer realitySize){

        if(realitySize > 9){

            //2个随机数   A 9位  B len/9 位
            String r1 = String.valueOf( RANDOM.nextInt(999999999) % (999999999 - 100000000 + 1) + 100000000);
            //递归
            String r2 = getRandomString(realitySize-9);
            return r1 + r2;


        }else{
            //1个当前位数随机
            int maxRandom = 0;
            int minRandom = 0;

            StringBuffer minSB = new StringBuffer(realitySize);
            StringBuffer maxSB = new StringBuffer(realitySize);
            for (int i = 0; i < realitySize; i++) {
                if(i == 0){
                    minSB.append("1");
                }else{
                    minSB.append("0");
                }
                maxSB.append("9");
            }
            maxRandom = Integer.valueOf(maxSB.toString());
            minRandom = Integer.valueOf(minSB.toString());

            int s =  RANDOM.nextInt(maxRandom) % (maxRandom - minRandom + 1) + minRandom;

            return String.valueOf(s);
        }

    }



    private static final StringBuilder SB = new StringBuilder();

    /**
     * 生成防伪码 集合
     * @param serialNumber  基础序列值[例如,区分不同用户的值,每个用户都可以生码,为何区分开各个用户的区别的基础序列值]
     * @param total 总共生成多少个
     * @return
     */
    public static List<String> getCode(String serialNumber, Integer total){

        List<String> codes = new ArrayList<String>();



        if(total == null){
            throw new CodePoolException(CodePoolException.ERROR_EMPTY_TOTAL);
        }
        //当前total的长度 位数
        int totalLen = total.toString().length();

        if(totalLen > CodePoolException.ONCE_MAX_SIZE){

            throw new CodePoolException(CodePoolException.ERROR_TOTAL_IS_TOO_LARGE);
        }


        int baseLength = total.toString().length();

        //开始生码
        for (Integer i = 0; i < total; i++) {

            SB.append(CodePoolException.CODE_VERSION);

            SB.append(serialNumber);

            String string = i.toString();

            string = CreateCodeUtil.fmtStringAddZero(string, baseLength, "0");

            int needLength = CodePoolException.CODE_SIZE - 9 - string.length();

            SB.append(string);
            SB.append(getRandomString(needLength));

            codes.add(SB.toString());

            SB.setLength(0);
        }

        return codes;

    }


}
