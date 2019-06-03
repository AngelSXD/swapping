package com.sxd.swapping.utils;


import com.sxd.swapping.WorkSheetUtils.DDLCreater;
import java.util.ArrayList;
import java.util.List;

public class WorkSheetTest {

    public static void main(String[] args) {

        DDLCreater creater = new DDLCreater(12L);

        List<String> list = new ArrayList<>();
        list.add("input-number_0");
        list.add("textarea_0");
        list.add("area_0");
        for (String s : list) {
            creater.addField(s);
        }
        creater.addField("phone_0",true);
        creater.addField("serial",true,"流水号是必填的");
        creater.addField("input-number_1",true,"数字输入框");
        String ddl = creater.getAddDDL();
        String ddl2 = creater.getDelDDL();
        System.out.println(ddl);
        System.out.println(ddl2);
    }
}
