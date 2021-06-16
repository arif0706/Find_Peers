package com.example.ideas.View.ChatScreen;

import com.example.ideas.Model.Chat.Message;

import java.util.List;

public interface ChatScreenView {
    void onFetchChat(List<Message> messageList);
    void addMessage(Message message);
    void onTyping(String userName);
    void onStoppedTyping();
}
