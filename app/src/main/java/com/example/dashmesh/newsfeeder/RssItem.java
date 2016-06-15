package com.example.dashmesh.newsfeeder;

/**
 * Created by dashmesh on 18/3/16.
 */
public class RssItem {
    // item title
    private String title;
    // item link
    private String link;
    // item subtitle
    private String description;
    private String pubDate;
    private String thumbnail;
    public String getDes() {
        return description;
    }
    public void setDes(String description) {
        this.description = description;
    }
    public String getPubDate() {
        return pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return title + pubDate + description + thumbnail;
    }
    }

