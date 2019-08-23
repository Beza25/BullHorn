package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;
    private String postedDate;
    private String sentBy;
    private String picture;


    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public void setSentBy(String sentby) {
        this.sentBy = sentby;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
