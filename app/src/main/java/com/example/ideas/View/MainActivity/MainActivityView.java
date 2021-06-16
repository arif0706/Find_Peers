package com.example.ideas.View.MainActivity;

import com.example.ideas.Model.User;

public interface MainActivityView {
    void onGetUserDetails(User user);
    void onUserUpdated(User user);
}
