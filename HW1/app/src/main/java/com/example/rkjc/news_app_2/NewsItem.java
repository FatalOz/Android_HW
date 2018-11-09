package com.example.rkjc.news_app_2;

public class NewsItem {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public NewsItem(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;

    }

    public String getTitle(){
        return title;
    }
    public String getPublishedAt() { return publishedAt; }
}
