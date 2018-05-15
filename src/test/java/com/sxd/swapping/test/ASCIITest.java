package com.sxd.swapping.test;

import org.junit.Test;

public class ASCIITest {

    @Test
    public void test(){
        System.out.println(getAscii("莲") > getAscii("电"));
    }

    public int getAscii(String str){
        int ascii = 0;
        byte[] bytes = str.getBytes();
        for (byte aByte : bytes) {
            ascii += Integer.valueOf(aByte);
        }
        System.out.println(ascii);

        return ascii;
    }
}
