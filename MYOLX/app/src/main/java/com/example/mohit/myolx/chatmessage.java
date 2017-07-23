package com.example.mohit.myolx;
import java.util.Date;
public class chatmessage {
    public String sender;
    public String message;
    public long time;
    public chatmessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
        time = new Date().getTime();
    }
    public chatmessage()
    {
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
}
