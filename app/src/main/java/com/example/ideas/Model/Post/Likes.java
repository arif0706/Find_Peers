package com.example.ideas.Model.Post;

import com.example.ideas.Model.User;

import java.util.List;

public class Likes {
    long no_of_likes;
    List<String> likers;

    public long getNo_of_likes() {
        return no_of_likes;
    }

    public void setNo_of_likes(long no_of_likes) {
        this.no_of_likes = no_of_likes;
    }

    public List<String> getLikers() {
        return likers;
    }

    public void setLikers(List<String> likers) {
        this.likers = likers;
    }
}
