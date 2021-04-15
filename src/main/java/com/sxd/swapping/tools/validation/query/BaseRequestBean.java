package com.sxd.swapping.tools.validation.query;

import javax.validation.constraints.NotNull;

/**
 * @Author: SXD
 * @Description:  基础请求Bean
 * @Date: create in 2020/1/10 14:27
 */
public class BaseRequestBean {

    @NotNull(message = "userId can't be  null")
    private Long userId;

    @NotNull(message = "store can't be  null")
    private String storeId;


    private String userName;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
