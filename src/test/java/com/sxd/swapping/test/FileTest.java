package com.sxd.swapping.test;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class FileTest {


    private Set<String> filesName = new HashSet<>();//文件夹名称
    private Set<String> fileType = new HashSet<>();//文件类型
    private int num=0;//满足条件的java文件总数
    private Set<Resource> allSet = new HashSet<>();//所有Resource信息

    private Map<String,String> CRUDMap = new HashMap<>();//记录所有CRUD基础接口信息

    private Map<String,String> lostMap = new HashMap<>();//记录所有未匹配到CRUD的接口的信息 留到第二遍去处理

    @Test
    public void readLines(){
        //将不需要计算入源码行数的 文件夹名字 存储起来
        filesName.add("target");
        filesName.add(".svn");
        filesName.add(".settings");
        filesName.add("test");
        filesName.add("bootstrap");
        filesName.add("images");
        filesName.add("layer");
        filesName.add("lib");
        filesName.add("META-INF");
        filesName.add("skin");
        filesName.add("temp");
        filesName.add("upload");

        //将需要计算入源码行数的文件类型  后缀 存储起来
        fileType.add(".java");

        //查询的根路径
        File file = new File("D:/document/IdeaProjects/pisen/pisen-cloud-luna");
        forLines(file);//调用迭代方法

        System.out.println(num);
    }


    /**
     * 用来迭代的方法
     * @param file
     */
    public void forLines(File file){
        if(file.isDirectory()){//是----文件夹
            File[] files = file.listFiles(new FilenameFilter() {//返回true，即为满足条件的文件或者文件夹 ，保存在文件数组中
                @Override
                public boolean accept(File file, String name) {
                    if(filesName.contains(name)){ //如果文件夹的名字符合集合内的任何一个，则排除
                        return false;
                    }else if(name.lastIndexOf(".") != -1  && fileType.contains(name.substring(name.lastIndexOf("."))) || new File(file,name).isDirectory()){
                        //如果file文件名中包含. 则是文件，否则是文件夹  &&  并且文件后缀是包含于文件类型集合
                        return true;
                    }
                    return false;
                }
            });
            //循环File[]数组
            for (File f : files) {
                forLines(f);
            }
        }else{//是----文件

            String fileName = file.getName();
            String path = file.getPath();

            //查询所有api文件
            if (fileName.contains("Api") && path.contains("\\api\\")){

                //api实现类全部排除
                if (!fileName.contains("ApiImpl.java")){

                    //记录满足条件的文件数量
                    num++;
//                    System.out.println("文件名："+fileName+"》》文件所在路径："+path);

                    //获取到一个文件中的所有内容
                    String str = readToString(path);

                    String[] split = str.split("@RequestMapping\\(");

                    for (String xxx : split){
                        if(xxx.toCharArray()[0] == '"'){
                            System.out.println(xxx.split("\\)")[0]);
                        }



                    }



                    //如果是基础ICRUDCommonApi<T> 记录下它的<地址:文本内容>
                    if (str.contains("ICRUDCommonApi<T>")){

                    }else{
                        //处理所有
                        if (str.contains("ICRUDCommonApi") && str.contains("extends")){

                        }
                    }
                }

            }

        }

    }


    public Set<Resource> dealCRUDMethod(String str){
        Set<Resource> set = new HashSet<>();
        return null;
    }




    /**
     * InputStream流的方式一次性读入文件所有内容为字符串
     * @param fileName
     * @return
     */
    public String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }




}
