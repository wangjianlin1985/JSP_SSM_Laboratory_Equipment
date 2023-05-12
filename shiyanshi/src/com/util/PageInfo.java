// 
// 
// 

package com.util;

import java.util.List;

public class PageInfo
{
    public final int PAGESIZE = 6;
    private int count;
    private List pageList;
    private int pageIndex;
    private int totalpages;
    
    public PageInfo() {
        this.pageIndex = 1;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public void setCount(final int count) {
        this.count = count;
    }
    
    public List getPageList() {
        return this.pageList;
    }
    
    public void setPageList(final List pageList) {
        this.pageList = pageList;
    }
    
    public int getPageIndex() {
        return this.pageIndex;
    }
    
    public void setPageIndex(final int pageIndex) {
        this.pageIndex = pageIndex;
    }
    
    public void setTotalpages(final int totalpages) {
        this.totalpages = totalpages;
    }
    
    public int getTotalpages() {
        this.totalpages = this.count / 6;
        if (this.count % 6 != 0) {
            ++this.totalpages;
        }
        return this.totalpages;
    }
}
