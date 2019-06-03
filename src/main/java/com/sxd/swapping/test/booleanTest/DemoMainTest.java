package com.sxd.swapping.test.booleanTest;

public class DemoMainTest {

    public static void main(String[] args) {
        Demo demo = new Demo();
demo.setFlag(false);
//        int num = demo.getNum();
       Boolean flag = demo.getFlag();
        String name = demo.getName();

//        System.out.println(num);
//        System.out.println(flag);
        System.out.println(name);

        if (flag == null || flag) {
            System.out.println("记住密码");
        }
    }
}
