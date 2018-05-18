package com.example.solar.newsproject;

public class NewData {

    private String title;
    private String content;
    private String img;
    private String link;

    public NewData(){}

    public NewData(String title) {
        this.title = title;
    }

    public NewData(String title,String content,String link){
        this.title = title;
        this.content = content;
        this.link = link;
    }

    public NewData(String title, String content, String img, String link) {
        this.title = title;
        this.content = content;
        this.img = img;
        this.link = link;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
