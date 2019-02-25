package com.sxd.swapping.test.fileTest;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.regex.Pattern;


/**
 * 1.循环获取 文件夹下的所有txt文件
 * 2.循环读取  txt文件内容的每一行
 * 3.将读取到的所有符合条件的内容  统一写出到新的txt文件中
 *
 */
public class DealRecepipt {

    public static void main(String[] args) {
        readFile();
    }


    private static  void readFile(){

        File wirteFile = new File("E:\\1\\所有防伪码.txt");
        try {
            wirteFile.createNewFile();
            FileWriter writer = new FileWriter(wirteFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            File file = new File("E:\\1\\入库失败数据");
            File[] files = file.listFiles();


            for (int i = 0; i < files.length; i++) {
                File file1 = files[i];
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file1.getAbsolutePath()));
                    String line;
                    while (StringUtils.isNotBlank(line = reader.readLine())){
                        System.out.println(line);
                        if (Pattern.matches("[0-9]*",line)){
                            bufferedWriter.write(line+"\r\n");
                        }
                    }
                    reader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            bufferedWriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
