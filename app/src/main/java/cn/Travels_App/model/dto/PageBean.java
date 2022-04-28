package cn.Travels_App.model.dto;


import java.util.List;

public class PageBean<E> {

    private List<E> data ;            // 存放实体类集合

    private int currentPage ;    // 当前页

    private int pageSize ;        // 每页显示的条数

    private int totalPage ;        // 总页数

    private int totalCount ;    // 总条数

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}