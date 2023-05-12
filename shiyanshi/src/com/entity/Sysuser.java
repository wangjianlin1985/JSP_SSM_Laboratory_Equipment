// 
// 
// 

package com.entity;

public class Sysuser
{
    private Integer uid;
    private String uname;
    private String sex;
    private String address;
    private String pwd;
    private String utype;
    private String tel;
    private String age;
    private String mbanswer;
    private String question;
    private String email;
    private String pubtime;
    private String tname;
    private String isdel;
    
    public Integer getUid() {
        return this.uid;
    }
    
    public void setUid(final Integer uid) {
        this.uid = uid;
    }
    
    public String getUname() {
        return this.uname;
    }
    
    public void setUname(final String uname) {
        this.uname = ((uname == null) ? null : uname.trim());
    }
    
    public String getSex() {
        return this.sex;
    }
    
    public void setSex(final String sex) {
        this.sex = ((sex == null) ? null : sex.trim());
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(final String address) {
        this.address = ((address == null) ? null : address.trim());
    }
    
    public String getPwd() {
        return this.pwd;
    }
    
    public void setPwd(final String pwd) {
        this.pwd = ((pwd == null) ? null : pwd.trim());
    }
    
    public String getUtype() {
        return this.utype;
    }
    
    public void setUtype(final String utype) {
        this.utype = ((utype == null) ? null : utype.trim());
    }
    
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(final String tel) {
        this.tel = ((tel == null) ? null : tel.trim());
    }
    
    public String getAge() {
        return this.age;
    }
    
    public void setAge(final String age) {
        this.age = ((age == null) ? null : age.trim());
    }
    
    public String getMbanswer() {
        return this.mbanswer;
    }
    
    public void setMbanswer(final String mbanswer) {
        this.mbanswer = ((mbanswer == null) ? null : mbanswer.trim());
    }
    
    public String getQuestion() {
        return this.question;
    }
    
    public void setQuestion(final String question) {
        this.question = ((question == null) ? null : question.trim());
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = ((email == null) ? null : email.trim());
    }
    
    public String getPubtime() {
        return this.pubtime;
    }
    
    public void setPubtime(final String pubtime) {
        this.pubtime = ((pubtime == null) ? null : pubtime.trim());
    }
    
    public String getTname() {
        return this.tname;
    }
    
    public void setTname(final String tname) {
        this.tname = ((tname == null) ? null : tname.trim());
    }
    
    public String getIsdel() {
        return this.isdel;
    }
    
    public void setIsdel(final String isdel) {
        this.isdel = ((isdel == null) ? null : isdel.trim());
    }
    
    @Override
    public String toString() {
        return "Sysuser [uid=" + this.uid + ", uname=" + this.uname + ", sex=" + this.sex + ", address=" + this.address + ", pwd=" + this.pwd + ", utype=" + this.utype + "]";
    }
}
