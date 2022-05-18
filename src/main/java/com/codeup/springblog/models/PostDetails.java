package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "post_details")
public class PostDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean isAwesome;
    private String historyOfPost;
    private String topicDescription;

    public long getId() {
        return id;
    }

    public PostDetails() {
    }


    public boolean getIsAwesome() {
        return isAwesome;
    }

    public boolean isAwesome() {
        return isAwesome;
    }

    public String getHistoryOfPost() {
        return historyOfPost;
    }

    public String getTopicDescription() {
        return topicDescription;
    }



}
