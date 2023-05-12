// 
// 
// 

package com.entity;

public class Sbbx
{
    private Integer wid;
    private Integer sysid;
    private Integer sbid;
    private String bxyy;
    private Integer uid;
    private String bxtime;
    private String bstatus;
    private Integer bxnum;
    private Integer cluid;
    private String cltime;
    
    public Integer getWid() {
        return this.wid;
    }
    
    public void setWid(final Integer wid) {
        this.wid = wid;
    }
    
    public Integer getSysid() {
        return this.sysid;
    }
    
    public void setSysid(final Integer sysid) {
        this.sysid = sysid;
    }
    
    public Integer getSbid() {
        return this.sbid;
    }
    
    public void setSbid(final Integer sbid) {
        this.sbid = sbid;
    }
    
    public String getBxyy() {
        return this.bxyy;
    }
    
    public void setBxyy(final String bxyy) {
        this.bxyy = ((bxyy == null) ? null : bxyy.trim());
    }
    
    public Integer getUid() {
        return this.uid;
    }
    
    public void setUid(final Integer uid) {
        this.uid = uid;
    }
    
    public String getBxtime() {
        return this.bxtime;
    }
    
    public void setBxtime(final String bxtime) {
        this.bxtime = ((bxtime == null) ? null : bxtime.trim());
    }
    
    public String getBstatus() {
        return this.bstatus;
    }
    
    public void setBstatus(final String bstatus) {
        this.bstatus = ((bstatus == null) ? null : bstatus.trim());
    }
    
    public Integer getBxnum() {
        return this.bxnum;
    }
    
    public void setBxnum(final Integer bxnum) {
        this.bxnum = bxnum;
    }
    
    public Integer getCluid() {
        return this.cluid;
    }
    
    public void setCluid(final Integer cluid) {
        this.cluid = cluid;
    }
    
    public String getCltime() {
        return this.cltime;
    }
    
    public void setCltime(final String cltime) {
        this.cltime = ((cltime == null) ? null : cltime.trim());
    }
}
