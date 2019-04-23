package com.sxd.swapping.test.fileTest;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理千万条数据
 * 拼接生成一组10000个数据的update SQL
 *
 * 处理txt文件大小400M
 * 速度：毫秒级别
 */
public class GetUpdateSQL {

    public static void main(String[] args) {

        //创建输出文件
        File file = new File("C:\\Users\\Administrator\\Desktop\\ES相关\\测试 启动SQL.txt");
        BufferedWriter bufferedWriter = null;
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);

            //初始化数据
            List<String> list = new ArrayList<>();

            //读取初始化文件
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\ES相关\\测试 启动.txt"));
            String line = null;
            int num = 1;
            //按行读取
            while (StringUtils.isNotBlank(line =  bufferedReader.readLine())){
                list.add(line);
                //如果满10000个 进行一次updateSQL的拼接，并清空 list
                if (list.size() == 10000){
                    System.out.println(">>>>>>>>>拼接"+num+"次");
                    String updateSql = getUpdateSql(list);

                    bufferedWriter.write(updateSql);
                    list.clear();
                    num++;
                }
            }


            //最后对list做一次处理[无论剩余list长度是多少]
            String endSQL = getUpdateSql(list);
            bufferedWriter.write(endSQL);
            list.clear();

            System.out.println(">>>>>>>>>>>>>处理完成");
            bufferedReader.close();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public  static  String getUpdateSql(List<String> list){

        StringBuffer sb = new StringBuffer();
        sb.append("update security_code_info set code_state = 1 where security_code in(");
        for (int i = 0; i < list.size(); i++) {
            sb.append("'"+list.get(i)+"'");
            if (i != list.size()-1){
                sb.append(",");
            }else {
                sb.append(");");
            }
        }
        sb.append("\r\n");
        return sb.toString();
    }
}
