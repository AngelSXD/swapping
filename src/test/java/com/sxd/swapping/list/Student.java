package com.sxd.swapping.list;

import com.alibaba.excel.annotation.ExcelProperty;

import java.util.List;

/**
 * @Author: SXD
 * @Description:
 * @Date: create in 2019/10/12 13:33
 */
public class Student {

    @ExcelProperty(value = "学生ID",index = 0)
    private Integer id;

    @ExcelProperty(value = "学生名称",index = 1)
    private String name;

    List<String> mcList;

    private Long age;

    public Student() {
    }

    public Student(Integer id, String name, Long age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public List<String> getMcList() {
        return mcList;
    }

    public void setMcList(List<String> mcList) {
        this.mcList = mcList;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
