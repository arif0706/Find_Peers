package com.example.ideas.View.ProfilePreview;

import com.example.ideas.Model.Post.Post;
import com.example.ideas.Model.User;

import java.util.List;

public interface ProfilePreviewView {
    void onGetUserDetails(User user);
    void onGetUserPosts(List<Post> posts);
}
