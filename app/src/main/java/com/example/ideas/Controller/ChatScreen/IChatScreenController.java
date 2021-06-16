package com.example.ideas.Controller.ChatScreen;

import com.example.ideas.Model.Chat.Message;
import com.example.ideas.Model.Chat.Typing;

public interface IChatScreenController {
    void startChat(Message message);
    void sendMessage(Message message);
    void onDisconnect();


    void getMessages(String chat_id);

    void onTyping(Typing typing);

    void onStoppedTyping(Typing typing);
}
