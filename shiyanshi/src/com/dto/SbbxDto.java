// 
// 
// 

package com.dto;

import com.entity.Sbbx;

public class SbbxDto extends Sbbx
{
    private String sysname;
    private String sbname;
    private String uname;
    private String cluname;
    
    public String getSysname() {
        return this.sysname;
    }
    
    public void setSysname(final String sysname) {
        this.sysname = sysname;
    }
    
    public String getSbname() {
        return this.sbname;
    }
    
    public void setSbname(final String sbname) {
        this.sbname = sbname;
    }
    
    public String getUname() {
        return this.uname;
    }
    
    public void setUname(final String uname) {
        this.uname = uname;
    }
    
    public String getCluname() {
        return this.cluname;
    }
    
    public void setCluname(final String cluname) {
        this.cluname = cluname;
    }
}
