package com.example.mohit.myolx;
public class chatbbox {
    public String ename;
    public String profurl;
    public String em;
    public chatbbox(String ename, String profurl, String em) {
        this.ename = ename;
        this.profurl = profurl;
        this.em = em;
    }
    public chatbbox()
    {
    }
    public void setEm(String em) {
        this.em = em;
    }
    public String getEm() {
        return em;
    }
    public String getEname() {
        return ename;
    }
    public String getProfurl() {
        return profurl;
    }
}
