package com.sxd.swapping.Bean;

import java.util.List;

/**
 * @Author: SXD
 * @Description:
 * @Date: create in 2020/4/3 14:46
 */
public class StuB {
    private Integer id;

    private String name;

    private List<StuB> children;


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

    public List<StuB> getChildren() {
        return children;
    }

    public void setChildren(List<StuB> children) {
        this.children = children;
    }

}
