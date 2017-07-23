package com.example.mohit.myolx;
public class ImageUpload {
    public String name;
    public String url;
    public int likes;
    public String loader;
    public int getLikes() {
        return likes;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setLoader(String loader) {
        this.loader = loader;
    }
    public String getUrl() {
        return url;
    }
    public String getLoader(){
        return this.loader;
    }
    public ImageUpload(String name,String url,String em, int likes){
        this.name = name;
        this.url = url;
        this.loader = em;
        this.likes = likes;
    }
    public ImageUpload(){
    }
}
