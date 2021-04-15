package com.sxd.swapping.mybatis.pojo;

import com.sxd.swapping.mybatis.base.BaseBean;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.*;

@Entity
@Table
@Getter
@Setter
public class HuaYangArea extends BaseBean implements Serializable {
    private static final long serialVersionUID = -1851783771574739215L;

    @Column(nullable = false)
    private String areaName;

    @Column(nullable = false)
    private Long areaPerson;


    public static Specification<HuaYangArea> where(HuaYangArea huaYangArea){

        return  new Specification<HuaYangArea>() {
            @Override
            public Predicate toPredicate(Root<HuaYangArea> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //创建查询列表
                List<Predicate> predicates = new ArrayList<>();

                //字段areaName是否查询
                String areaName = huaYangArea.getAreaName();
                if (StringUtils.isNotBlank(areaName)){
                    predicates.add(criteriaBuilder.like(root.get("areaName"),"%"+areaName+"%"));
                }
                //字段areaPerson是否查询
                Long areaPerson = huaYangArea.getAreaPerson();
                if (areaPerson != null) {
                    predicates.add(criteriaBuilder.equal(root.get("areaPerson"),areaPerson));
                }

                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
    }

}
