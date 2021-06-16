package com.example.ideas.Model.Post;

import com.example.ideas.Model.User;

import java.util.List;

public class Dislikes {
    long no_of_dislikes;
    List<String> dislikers;

    public long getNo_of_dislikes() {
        return no_of_dislikes;
    }

    public void setNo_of_dislikes(long no_of_dislikes) {
        this.no_of_dislikes = no_of_dislikes;
    }

    public List<String> getDislikers() {
        return dislikers;
    }

    public void setDislikers(List<String> dislikers) {
        this.dislikers = dislikers;
    }
}
