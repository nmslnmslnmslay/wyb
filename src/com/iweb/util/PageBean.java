package com.iweb.util;

import java.util.List;

/**
 * 封装分页信息
 * @author azzhu
 * @create 2019-08-13 15:48:18
 */
public class PageBean<T> {
    private Integer pageNum;    //当前页  从前端传递过来
    private Integer pageSize = 5;   //每页的条数
    private Integer totalPages;     //总页数  计算出来的
    private Integer totalCount;     //总记录数，使用sql查询出来的
    private List<T> listData;    //每页的list数据
    //可以封装更多信息，比如是否有下一页，上一页、下一页等等

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

    /**
     * 计算总页数
     * @return
     */
    public Integer getTotalPages() {
        if(this.getTotalCount() % this.pageSize == 0) {
            this.totalPages = this.getTotalCount() / this.pageSize;
        } else {
            this.totalPages = this.getTotalCount() / this.pageSize + 1;
        }
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getListData() {
        return listData;
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalPages=" + getTotalPages() +
                ", totalCount=" + totalCount +
                ", listData=" + getListData() +
                '}';
    }
}
