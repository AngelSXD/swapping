package com.sxd.swapping.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: SXD
 * @Description:
 * @Date: create in 2019/10/10 11:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExceptionTest {


    public static void main(String[] args) {

        System.out.println(deal());
    }



    public static boolean deal(){

        try {
            System.out.println("进入try");
            int a = 1/0;
            return true;
        }catch (Exception e){
            System.out.println("进入catch");
            return  false;
        }finally {
            System.out.println("进入finally");
        }
    }

    @Test
    public void test(){

        try {
            dealException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void dealException() throws Exception{

        try {
            System.out.println("进入执行");
            int a = 10/0;
        }catch (Exception e){
            throw new Exception("除0异常");
        }
        System.out.println("还在执行");
    }


}
