package com.example.mohit.myolx;


public class userinfo {
    public String name,mobile,coun,stat,bday,scho,coll,email,profurl,timeurl;
    public userinfo(String name,String mob,String scho,String coll,String stat,String coun,String bday,String email,String x,String y)
    {
        this.name = name;
        this.mobile = mob;
        this.scho = scho;
        this.coll = coll;
        this.stat = stat;
        this.coun = coun;
        this.bday = bday;
        this.email = email;
        this.profurl = x;
        this.timeurl = y;
    }

    public userinfo(){

    }

    public String getName()
    {
        return this.name;
    }

    public String getCoun()
    {
        return this.coun;
    }

    public String getColl()
    {
        return this.coll;
    }

    public String getStat()
    {
        return this.stat;
    }

    public String getScho()
    {
        return this.scho;
    }

    public String getBday()
    {
        return this.bday;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getProfurl(){
        return this.profurl;
    }

    public String getTimeurl() {
        return timeurl;
    }
}
