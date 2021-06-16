package com.example.ideas.Controller.MainActivity.Groups;

import android.content.Context;
import android.content.Intent;

import com.example.ideas.Controller.RetrofitClient;
import com.example.ideas.Model.Group;
import com.example.ideas.View.ChatScreen.ChatScreen;
import com.example.ideas.View.MainActivity.Groups.Chat.ChatListScreenView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatListScreenController implements IChatListScreenController{


    ChatListScreenView chatListScreenView;
    public ChatListScreenController(ChatListScreenView chatListScreenView){
        this.chatListScreenView=chatListScreenView;
    }
    public void getGroups(String id) {
        Call<List<Group>> call= RetrofitClient.getInstance().getMyApi().getGroups(id);
        call.enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {
                chatListScreenView.onChatsFetched(response.body());
            }

            @Override
            public void onFailure(Call<List<Group>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onGroupClick(Context context, String chat_id,String group_name) {
        Intent intent=new Intent(context, ChatScreen.class);
        intent.putExtra("chat_id",chat_id);
        intent.putExtra("group_name",group_name);
        context.startActivity(intent);
    }
}
