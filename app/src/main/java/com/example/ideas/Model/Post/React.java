package com.example.ideas.Model.Post;

public class React {
   public  String post_id;
    public String IdToken;
    public String user_id;
    public boolean like;
    public boolean dislike;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getIdToken() {
        return IdToken;
    }

    public void setIdToken(String idToken) {
        this.IdToken = idToken;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isDislike() {
        return dislike;
    }

    public void setDislike(boolean dislike) {
        this.dislike = dislike;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }
}
