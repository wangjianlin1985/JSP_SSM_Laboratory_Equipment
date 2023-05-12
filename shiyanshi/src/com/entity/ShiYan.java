// 
// 
// 

package com.entity;

public class ShiYan
{
    private Integer id;
    private String name;
    private String address;
    private String pubtime;
    private String isdel;
    private String uid;
    private String stime;
    private String ftype;
    private String mstatus;
    private Integer snum;
    private String miaoshu;
    
    public String getStime() {
        return this.stime;
    }
    
    public void setStime(final String stime) {
        this.stime = stime;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = ((name == null) ? null : name.trim());
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(final String address) {
        this.address = ((address == null) ? null : address.trim());
    }
    
    public String getPubtime() {
        return this.pubtime;
    }
    
    public void setPubtime(final String pubtime) {
        this.pubtime = ((pubtime == null) ? null : pubtime.trim());
    }
    
    public String getIsdel() {
        return this.isdel;
    }
    
    public void setIsdel(final String isdel) {
        this.isdel = ((isdel == null) ? null : isdel.trim());
    }
    
    public String getUid() {
        return this.uid;
    }
    
    public void setUid(final String uid) {
        this.uid = ((uid == null) ? null : uid.trim());
    }
    
    public String getFtype() {
        return this.ftype;
    }
    
    public void setFtype(final String ftype) {
        this.ftype = ((ftype == null) ? null : ftype.trim());
    }
    
    public String getMstatus() {
        return this.mstatus;
    }
    
    public void setMstatus(final String mstatus) {
        this.mstatus = ((mstatus == null) ? null : mstatus.trim());
    }
    
    public Integer getSnum() {
        return this.snum;
    }
    
    public void setSnum(final Integer snum) {
        this.snum = snum;
    }
    
    public String getMiaoshu() {
        return this.miaoshu;
    }
    
    public void setMiaoshu(final String miaoshu) {
        this.miaoshu = ((miaoshu == null) ? null : miaoshu.trim());
    }
    
    @Override
    public String toString() {
        return "ShiYan [id=" + this.id + ", name=" + this.name + ", address=" + this.address + ", pubtime=" + this.pubtime + ", isdel=" + this.isdel + ", uid=" + this.uid + ", ftype=" + this.ftype + ", mstatus=" + this.mstatus + ", snum=" + this.snum + ", miaoshu=" + this.miaoshu + "]";
    }
}
