package com.sxd.swapping.base;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 基础bean
 */
@MappedSuperclass
public class BaseBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// 主键 自增

    @Column(nullable = false, updatable = false)
    private Date createDate = new Date();// 创建时间

    private Date updateDate = new Date();// 修改时间

    private String updateId; // 修改人

    @Column(nullable = false)
    private String createId; // 创建人


    @Column(nullable = false)
    private String uid;     //业务主键

    @Transient
    private Integer pageNum = 0;    //分页 页号

    @Transient
    private Integer pageSize = 10;  //分页 页量


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void initEntity(){
        this.createDate = new Date();
        this.createId = UUID.randomUUID().toString();//如果有当前登陆人，则初始化为当前登陆人
        this.uid = UUID.randomUUID().toString();
    }

    public void updateEntity(){
        this.updateDate = new Date();
        this.updateId = UUID.randomUUID().toString();//如果有当前登陆人，则赋值为当前登陆人
    }

    /**
     * 分页 工具方法
     * @return
     */
    public  PageRequest page(){
        return PageRequest.of(pageNum,this.pageSize);
    }

    /**
     * 分页 排序工具方法
     *
     * 中文字段排序 需要在查询出来后处理 sort无法解决中文排序的问题
     * @param map
     * @param obj
     * @return
     * @throws Exception
     */
    public  PageRequest page(Map<String,Sort.Direction> map,Object obj) throws Exception{
        //反射获取实体所有属性
        List<String> properties = Arrays.stream(obj.getClass().getDeclaredFields()).map(i->i.getName()).collect(Collectors.toList());
        Set<String> keySet = map.keySet();
        Sort sort = null;
        if (properties.containsAll(keySet)){
            for (String str:keySet){
                if (sort == null){
                    sort = Sort.by(map.get(str),str);
                }else{
                    sort = sort.and(Sort.by(map.get(str),str));
                }
            }
        }else{
            throw new Exception("排序字段非本实体字段");
        }
        return PageRequest.of(this.pageNum,this.pageSize,sort);
    }
}
