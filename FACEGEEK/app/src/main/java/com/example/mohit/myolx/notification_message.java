package com.example.mohit.myolx;

public class notification_message {
    public String type;
    public String uploader;

    public notification_message(String uploader, String type) {
        this.uploader = uploader;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }


    public notification_message()
    {

    }
}
