package com.sxd.swapping.string;

import java.io.Serializable;

public class PromotionEnjoyValueCO implements Serializable  {

    private static final long serialVersionUID = -8777382639322495868L;
    /** 促销id */
    private Long proId;
    /** 促销触发条件id */
    private Long proConditionId;
    /** 触发优惠值顺序 */
    private Integer stepValue;
    /** 触发值 */
    private String triggerValue;
    /** 优惠类型 */
    private Integer rewardType;
    /** 优惠值 */
    private Integer rewardValue;
    /** 最大优惠值 */
    private Integer rewardMaxValue;
    /** 优惠商品组号 */
    private Integer enjoyItemGroup;

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public Long getProConditionId() {
        return proConditionId;
    }

    public void setProConditionId(Long proConditionId) {
        this.proConditionId = proConditionId;
    }

    public Integer getStepValue() {
        return stepValue;
    }

    public void setStepValue(Integer stepValue) {
        this.stepValue = stepValue;
    }

    public String getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(String triggerValue) {
        this.triggerValue = triggerValue;
    }

    public Integer getRewardType() {
        return rewardType;
    }

    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    public Integer getRewardValue() {
        return rewardValue;
    }

    public void setRewardValue(Integer rewardValue) {
        this.rewardValue = rewardValue;
    }

    public Integer getRewardMaxValue() {
        return rewardMaxValue;
    }

    public void setRewardMaxValue(Integer rewardMaxValue) {
        this.rewardMaxValue = rewardMaxValue;
    }

    public Integer getEnjoyItemGroup() {
        return enjoyItemGroup;
    }

    public void setEnjoyItemGroup(Integer enjoyItemGroup) {
        this.enjoyItemGroup = enjoyItemGroup;
    }
}
