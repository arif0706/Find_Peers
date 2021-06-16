package com.example.ideas.Model;

import com.example.ideas.Model.Post.Post;

import java.util.List;

public class User {

    String email;
    String fullname;
    String photo;
    String password;
    int no_of_posts;
    String _id;
    String fcm_id_token;
    String bio;
    List<String> skills;
    List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getFcm_id_token() {
        return fcm_id_token;
    }

    public void setFcm_id_token(String fcm_id_token) {
        this.fcm_id_token = fcm_id_token;
    }

    List<String> categories;
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getNo_of_posts() {
        return no_of_posts;
    }

    public void setNo_of_posts(int no_of_posts) {
        this.no_of_posts = no_of_posts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
