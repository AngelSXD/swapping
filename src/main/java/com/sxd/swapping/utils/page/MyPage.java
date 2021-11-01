package com.sxd.swapping.utils.page;

import java.util.List;

public class MyPage<T> {
    public final static int DEFAULT_PAGE_SIZE = 10;
    public final static int DEFAULT_CURRENT_PAGE = 1;

    private Integer currentPage = DEFAULT_CURRENT_PAGE;
    private Integer pageCount;
    private Integer totalCount;
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    private List<T> result;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        if(null != currentPage){
            this.currentPage = currentPage;
        }
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(null != pageSize){
            this.pageSize = pageSize;
        }
    }

    public Boolean getFirstPage() {
        return currentPage == DEFAULT_CURRENT_PAGE;
    }

    public Boolean getLastPage() {
        return currentPage.equals(pageCount);
    }

    public Integer getNextPage() {
        return currentPage + 1;
    }

    public Integer getPreviousPage() {
        return currentPage - 1;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Integer getStart(){
       return (getCurrentPage() -1) * getPageSize();
    }
}
