package com.sxd.swapping.getOrPostRequest;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sxd.swapping.SwappingApplicationTests;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GetOrPostRequest extends SwappingApplicationTests {


//url  http://man.marketing-items.dmall.com/check/querySku?currentPage=1&pageSize=10000&ruleId=201912060015979&skuId=&ruleType=2
    @Test
    public void deal() throws IOException{

        //最后要输出的结果集
        List<String> results = new ArrayList<>();

        //一.拼接批量请求的url地址
        List<String> urls = getUrls();
        int sign = 1;


        //二.初始化请求端
        CloseableHttpClient closeableHttpClient =  HttpClientBuilder.create().build();
        for (String url : urls) {

            //三.请求10次 睡眠1s
//            if (sign % 10 == 0) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

            //四.打印 请求标记
            System.out.println("请求第"+sign+"次");
            sign ++;


            //五.发送请求核心代码
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse closeableHttpResponse = null;
            try {
                closeableHttpResponse = closeableHttpClient.execute(httpGet);
                HttpEntity entity = closeableHttpResponse.getEntity();
                String response = EntityUtils.toString(entity);
                System.out.println(response);



                //六。处理业务
                JSONObject jsonObject = JSONObject.parseObject(response);
                if ("0".equals(jsonObject.getString("code"))) {
                    JSONArray array = JSONArray.parseArray(jsonObject.getString("data"));
                    for (Object o : array) {
                        JSONObject obj = (JSONObject)o;
                        System.out.println(obj.getString("skuId"));
                        results.add(obj.getString("skuId"));
                    }
                }



                closeableHttpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        //七.把结果集输出成txt文件
        writeTxt(results);
    }

    /**
     * 获取get请求的URL地址
     * @return
     */
    public   List<String> getUrls() throws IOException{
        List<String> urls = new ArrayList<>();
        StringBuffer sb = new StringBuffer();


        List<String> ptoIds = getProIds();

        for (String ptoId : ptoIds) {
            sb.append("http://man.marketing-items.dmall.com/check/querySku?currentPage=1&pageSize=10000&ruleId=");
            sb.append(ptoId.trim()).append("&skuId=&ruleType=2");
            String url = sb.toString();
            urls.add(url);
            sb.setLength(0);
        }

        return urls;
    }


    /**
     * 文件读取 获取id
     * @return
     * @throws IOException
     */
    public  List<String> getProIds() throws IOException{
        List<String> proIds = new ArrayList<>();

        //1.读取文件
        File file = new File("d:/new 1.txt");
        InputStreamReader read = new InputStreamReader(new FileInputStream(file),"utf-8");//考虑到编码格式
        BufferedReader bu = new BufferedReader(read);

        //2.拼接字符串
        String lineText = null;
        while((lineText = bu.readLine()) != null){
            proIds.add(lineText);
        }
        read.close();

        return proIds;
    }



    /**
     * 输出结果集
     */
    public void writeTxt(List<String> results) throws IOException{
        //3.边读边写
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File("d:/写出文件.txt")));
        BufferedWriter wr = new BufferedWriter(out);
        for (String result : results) {
            wr.write(result+"\r\n");
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>文件输出完成");
        wr.close();
    }



}
