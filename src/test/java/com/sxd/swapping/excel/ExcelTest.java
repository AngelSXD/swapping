package com.sxd.swapping.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.sxd.swapping.list.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SXD
 * @Description:
 * @Date: create in 2019/11/2 15:01
 */
public class ExcelTest {

    public static void main(String[] args) {
        String filePath = "E:\\owner\\test.xlsx";

        ExcelWriter excelWriter = EasyExcel.write(filePath, Student.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("促销商品数据").build();


        List<Student> list = new ArrayList<>();
        int i = 10;
        for (int i1 = 0; i1 < i; i1++) {
            Student student = new Student();
            student.setId(i1);
            student.setName("学生"+i1);

            list.add(student);
        }

        excelWriter.write(list, writeSheet);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }
}
