package com.example.ideas.Controller.MainActivity.Home;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.ideas.Model.Post.GetPost;
import com.example.ideas.Model.JoinGroup;
import com.example.ideas.Model.Post.React;

public interface IHomeScreenController {
    void onJoinGroup(JoinGroup joinGroup);
    void getPosts(String email_id);

    void showOptionsBottomSheet(Context context, FragmentManager fragmentManager, GetPost post);
    void reactPost(React post_id);
}
