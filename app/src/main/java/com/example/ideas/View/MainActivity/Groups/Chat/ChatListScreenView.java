package com.example.ideas.View.MainActivity.Groups.Chat;

import com.example.ideas.Model.Group;

import java.util.List;

public interface ChatListScreenView {
    void onChatsFetched(List<Group> groupList);
}
