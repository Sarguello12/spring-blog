package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private String body;
    @Column
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(String title, String body, String imgUrl, User user) {
        this.title = title;
        this.body = body;
        this.imgUrl = imgUrl;
        this.user = user;
    }

    public Post() {}

    public Post(String title, String body, String imgUrl) {
        this.title = title;
        this.body = body;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
