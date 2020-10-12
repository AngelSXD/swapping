package com.sxd.swapping.copyUtil;

import com.sxd.swapping.Bean.StuA;
import com.sxd.swapping.Bean.StuB;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SXD
 * @Description:
 * @Date: create in 2020/4/3 14:47
 */
public class BeanCopyStu {

    public static List<StuB>  copyBeans(List<StuA> vos){

        if (vos == null) {
            return null;
        }
        List<StuB> listB = new ArrayList<>();
        for (StuA vo : vos) {
            listB.add(copyOne(vo));
        }

        return listB;
    }

    public static StuB copyOne(StuA v){
        StuB stuB = new StuB();
        stuB.setId(v.getId());
        stuB.setName(v.getName());
        stuB.setChildren(copyBeans(v.getChildren()));

        return stuB;
    }
}
