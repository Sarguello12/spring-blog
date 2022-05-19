package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "post_images")
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String image_title;
    private String url;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public PostImage() {
    }

    public PostImage(String image_title, String url, Post post) {
        this.image_title = image_title;
        this.url = url;
        this.post = post;
    }

    public long getId() {
        return id;
    }

    public String getImage_title() {
        return image_title;
    }

    public String getUrl() {
        return url;
    }

    public Post getPost() {
        return post;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImage_title(String image_title) {
        this.image_title = image_title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}





