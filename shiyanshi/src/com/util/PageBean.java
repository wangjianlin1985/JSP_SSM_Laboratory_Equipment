// 
// 
// 

package com.util;

public class PageBean
{
    private int total;
    private int totalPage;
    private int page;
    private int pageSize;
    private int start;
    public static final int PAGESIZE = 6;
    
    public int getTotal() {
        return this.total;
    }
    
    public void setTotal(final int total) {
        this.total = total;
    }
    
    public int getTotalPage() {
        return (this.total % this.pageSize == 0) ? (this.total / this.pageSize) : (this.total / this.pageSize + 1);
    }
    
    public void setTotalPage(final int totalPage) {
        this.totalPage = totalPage;
    }
    
    public PageBean(final int page, final int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }
    
    public int getPage() {
        return this.page;
    }
    
    public void setPage(final int page) {
        this.page = page;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
    
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }
    
    public int getStart() {
        return (this.page - 1) * this.pageSize;
    }
}
