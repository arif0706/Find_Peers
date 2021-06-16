
package com.example.ideas.Model.Post;

import com.example.ideas.Model.Group;
import com.example.ideas.Model.User;

import java.util.List;

public class Post {

    public String title;
    public String _id;
    public String text;
    public String author_id;
    public String date;
    public String IdToken;
    public User postedBy;

    public List<String> categories;

    public Group group;
    public Likes likes;
    public Dislikes dislikes;





    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Dislikes getDislikes() {
        return dislikes;
    }

    public void setDislikes(Dislikes dislikes) {
        this.dislikes = dislikes;
    }

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
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return postedBy;
    }

    public void setUser(User postedBy) {
        this.postedBy = postedBy;
    }

    public String getIdToken() {
        return IdToken;
    }

    public void setIdToken(String idToken) {
        IdToken = idToken;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
