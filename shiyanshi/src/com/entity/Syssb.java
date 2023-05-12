// 
// 
// 

package com.entity;

public class Syssb
{
    private int id;
    private int sid;
    private int sbid;
    private int snum;
    private String time;
    
    public String getTime() {
        return this.time;
    }
    
    public void setTime(final String time) {
        this.time = time;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public int getSid() {
        return this.sid;
    }
    
    public void setSid(final int sid) {
        this.sid = sid;
    }
    
    public int getSbid() {
        return this.sbid;
    }
    
    public void setSbid(final int sbid) {
        this.sbid = sbid;
    }
    
    public int getSnum() {
        return this.snum;
    }
    
    public void setSnum(final int snum) {
        this.snum = snum;
    }
    
    @Override
    public String toString() {
        return "Syssb{id=" + this.id + ", sid=" + this.sid + ", sbid=" + this.sbid + ", snum=" + this.snum + ", time='" + this.time + '\'' + '}';
    }
}
