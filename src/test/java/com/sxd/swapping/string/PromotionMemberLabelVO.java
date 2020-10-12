package com.sxd.swapping.string;

import java.util.List;
import java.util.Objects;

public class PromotionMemberLabelVO {

    private String id;
    /** 商家ID */
    private List<Long> venderIds;

    /** 会员标签Code 唯一标识 */
    private String code;

    /** 会员标签名称 */
    private String name;

    /** 会员标签 值  例如： 军官士兵标签，值有 1和 0    麦德龙标签，值有 1~400  */
    private String value;

    /** 会员标签 维度 参照枚举MemberLabelDimenEnum */
    private Integer dimen;

    /**
     * 子级标签
     */
    private List<PromotionMemberLabelVO> children;
    /**
     * 是否包含了完整得标签信息
     * 【麦德龙标签 父级标签就没有完整信息  因为麦德龙标签没有值，而麦德龙的400个值对应的 子节点 就是有效的节点】
     */
    private Boolean isValid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Long> getVenderIds() {
        return venderIds;
    }

    public void setVenderIds(List<Long> venderIds) {
        this.venderIds = venderIds;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDimen() {
        return dimen;
    }

    public void setDimen(Integer dimen) {
        this.dimen = dimen;
    }

    public List<PromotionMemberLabelVO> getChildren() {
        return children;
    }

    public void setChildren(List<PromotionMemberLabelVO> children) {
        this.children = children;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionMemberLabelVO that = (PromotionMemberLabelVO) o;
        if (code == null || value == null || dimen == null) return false;
        return code.equals(that.code) &&
                value.equals(that.value) &&
                dimen.equals(that.dimen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, value, dimen);
    }
}
