// 
// 
// 

package com.entity;

public class Sbbf
{
    private Integer bid;
    private Integer sysid;
    private Integer sbid;
    private Integer bfsnum;
    private String bftime;
    private String bfyy;
    private Integer uid;
    private String status;
    private Integer cluid;
    private String cltime;
    
    public Integer getBid() {
        return this.bid;
    }
    
    public void setBid(final Integer bid) {
        this.bid = bid;
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
    
    public Integer getBfsnum() {
        return this.bfsnum;
    }
    
    public void setBfsnum(final Integer bfsnum) {
        this.bfsnum = bfsnum;
    }
    
    public String getBftime() {
        return this.bftime;
    }
    
    public void setBftime(final String bftime) {
        this.bftime = ((bftime == null) ? null : bftime.trim());
    }
    
    public String getBfyy() {
        return this.bfyy;
    }
    
    public void setBfyy(final String bfyy) {
        this.bfyy = ((bfyy == null) ? null : bfyy.trim());
    }
    
    public Integer getUid() {
        return this.uid;
    }
    
    public void setUid(final Integer uid) {
        this.uid = uid;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(final String status) {
        this.status = ((status == null) ? null : status.trim());
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
