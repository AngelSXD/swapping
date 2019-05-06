package com.sxd.swapping.utils;


import com.sxd.swapping.WorkSheetUtils.DDLCreater;
import java.util.ArrayList;
import java.util.List;

public class WorkSheetTest {

    public static void main(String[] args) {

        DDLCreater creater = new DDLCreater(12L);

        List<String> list = new ArrayList<>();
        list.add("input_0");
        list.add("textarea_0");
        list.add("area_0");
        for (String s : list) {
            creater.addField(s);
        }
        creater.addField("phone_0",true);
        String ddl = creater.getDDL();
        System.out.println(ddl);
    }
}
