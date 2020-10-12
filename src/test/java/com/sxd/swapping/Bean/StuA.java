package com.sxd.swapping.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: SXD
 * @Description:
 * @Date: create in 2020/4/3 14:45
 */
public class StuA implements Serializable {

    private static final long serialVersionUID = 7846249273091889924L;
    private Integer id;

    private String name;

    private List<StuA> children;
    private List<String> list;

    private StuA parent;

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

    public List<StuA> getChildren() {
        return children;
    }

    public void setChildren(List<StuA> children) {
        this.children = children;
    }

    public StuA getParent() {
        return parent;
    }

    public void setParent(StuA parent) {
        this.parent = parent;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
