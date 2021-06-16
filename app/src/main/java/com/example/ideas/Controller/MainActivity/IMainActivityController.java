package com.example.ideas.Controller.MainActivity;

import com.example.ideas.Model.User;

public interface IMainActivityController {
    void getUserDetails(String email_id);

    void UpdateUser(User user);
}
