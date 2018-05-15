package com.sxd.swapping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 多个字段 key2 和key3 建立唯一索引，需要 这两个字段都nullable = false，才能创建成功
 * 也可以创建普通索引，例如user_name 或者password
 */
@Entity
@Table(name = "model_entity",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"key2","key3"})
        },
        indexes = {
            @Index(columnList = "user_name"),
            @Index(columnList = "password")
        })
@Getter
@Setter
public class ModelEntity{


    @Id
    @Column(length = 36)
    private String key1;

    @Column(nullable = false,length = 32)
    private String key2;

    @Column(nullable = false,length = 32)
    private String key3;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

}
