package com.example.ideas.Controller.ProfilePreview;

import com.example.ideas.Model.Requests.Request;
import com.example.ideas.Model.Requests.Requests_list;
import com.example.ideas.Model.User;

public interface IProfilePreviewController {

    void getUserDetails(User user);
    void getUserPosts(String user_id);

    void onAcceptClicked(String request_id);
    void onRejectClicked();
}
