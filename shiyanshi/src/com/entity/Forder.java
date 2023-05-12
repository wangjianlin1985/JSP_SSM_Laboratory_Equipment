// 
// 
// 

package com.entity;

public class Forder
{
    private Integer kid;
    private Integer uid;
    private Integer fid;
    private Integer sid;
    private String status;
    private String pj;
    private String stime;
    private String etime;
    private String ftype;
    private String isdel;
    private String pubtime;
    private Integer snum;
    
    public Integer getKid() {
        return this.kid;
    }
    
    public void setKid(final Integer kid) {
        this.kid = kid;
    }
    
    public Integer getUid() {
        return this.uid;
    }
    
    public void setUid(final Integer uid) {
        this.uid = uid;
    }
    
    public Integer getFid() {
        return this.fid;
    }
    
    public void setFid(final Integer fid) {
        this.fid = fid;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(final String status) {
        this.status = ((status == null) ? null : status.trim());
    }
    
    public String getPj() {
        return this.pj;
    }
    
    public void setPj(final String pj) {
        this.pj = ((pj == null) ? null : pj.trim());
    }
    
    public String getStime() {
        return this.stime;
    }
    
    public void setStime(final String stime) {
        this.stime = ((stime == null) ? null : stime.trim());
    }
    
    public String getEtime() {
        return this.etime;
    }
    
    public void setEtime(final String etime) {
        this.etime = ((etime == null) ? null : etime.trim());
    }
    
    public String getFtype() {
        return this.ftype;
    }
    
    public void setFtype(final String ftype) {
        this.ftype = ftype;
    }
    
    public String getIsdel() {
        return this.isdel;
    }
    
    public void setIsdel(final String isdel) {
        this.isdel = ((isdel == null) ? null : isdel.trim());
    }
    
    public String getPubtime() {
        return this.pubtime;
    }
    
    public void setPubtime(final String pubtime) {
        this.pubtime = ((pubtime == null) ? null : pubtime.trim());
    }
    
    public Integer getSnum() {
        return this.snum;
    }
    
    public void setSnum(final Integer snum) {
        this.snum = snum;
    }
    
    public Integer getSid() {
        return this.sid;
    }
    
    public void setSid(final Integer sid) {
        this.sid = sid;
    }
    
    @Override
    public String toString() {
        return "Forder{kid=" + this.kid + ", uid=" + this.uid + ", fid=" + this.fid + ", sid=" + this.sid + ", status='" + this.status + '\'' + ", pj='" + this.pj + '\'' + ", stime='" + this.stime + '\'' + ", etime='" + this.etime + '\'' + ", ftype='" + this.ftype + '\'' + ", isdel='" + this.isdel + '\'' + ", pubtime='" + this.pubtime + '\'' + ", snum=" + this.snum + '}';
    }
}
