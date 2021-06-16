package com.example.ideas.View.MainActivity.Home;

import com.example.ideas.Model.Post.GetPost;
import com.example.ideas.Model.User;

import java.util.List;

public interface HomeScreenView {
    void onJoinedSuccess(String group_name);
    void onGetPosts(List<GetPost> postList);
    void onGetUserDetails(User user);
    void onSharePost(String address);
}
