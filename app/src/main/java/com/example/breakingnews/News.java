package com.example.breakingnews;

public class News {
    public String uid;
    public String description;
    public String name;
    public String photo;
    public String newsDate;

    public News(String uid, String description, String name, String photo, String newsDate) {
        this.uid = uid;
        this.description = description;
        this.name = name;
        this.photo = photo;
        this.newsDate = newsDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }
}
