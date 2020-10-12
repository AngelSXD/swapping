package com.sxd.swapping.enums;

import java.io.Serializable;

/**
 * 自定义枚举
 */
public enum MyEnum implements Serializable {

    ALL(0,"全部"),
    ONE(1,"第一个"),
    TWO(2,"第二个");


    //值
    int value;

    //描述
    String desc;

    MyEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }


    /**
     * 根据值  获取 枚举
     * @param value
     * @return
     */
    public static MyEnum valueOf(int value) {
        MyEnum[] instances = MyEnum.values();
        for(MyEnum instance : instances){
            if(instance.getValue() ==  value){
                return instance;
            }
        }

       return null;
    }
}
