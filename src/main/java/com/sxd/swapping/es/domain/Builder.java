package com.sxd.swapping.es.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.Id;
import java.util.Date;


@Setter
@Getter

//ES的三个注解
//指定index索引名称为项目名   指定type类型名称为实体名
@Document(indexName = "swapping",type = "builder")
//相当于ES中的mapping    注意对比文件中的json和原生json  最外层的key是没有的
@Mapping(mappingPath = "/esConfig/builder-mapping.json")
//相当于ES中的settings   注意对比文件中的json和原生json  最外层的key是没有的

@Setting(settingPath = "/esConfig/builder-setting.json")
public class Builder {

    //id  测试长整型数据   注意与es中索引本身id区分开
    @Id
    private Long id;


    //在创建初始化索引开始   就要去查看mapping是否ik分词创建成功   否则 需要进行索引数据的迁移操作


    //指定查询分词器 为ik分词器     存储分词器为 ik分词器
    //在@Field中指定的ik分词器没起作用，因此采用上面的两个注解 可以完全自定义类型Field的各个属性
    //@Field(searchAnalyzer = "ik_max_word",analyzer = "ik_max_word")


    //类型定义为text 可测试ik分词  繁简体转化   pinyin分词 查询效果
    //名称     测试字符串类型
    private String buildName;

    //类型定义为text  可测试大文本
    private String remark;

    //类型定义为keyword 可测试是否分词 以及查询效果
    private String email;

    //数量  测试整型数据
    private int buildNum;

    //时间也可以进行范围查询，但是查询传入参数，应该为mapping中定义的时间字段的 格式化字符串  或 时间戳 否则，ES无法解析格式会报错
    //时间    测试时间类型
    private Date buildDate;

    //积分比率  测试浮点型数据
    private Double integral;


    //分页大小
    private Integer pageNum = 0;
    //分页数量
    private Integer pageSize = 10;





}
