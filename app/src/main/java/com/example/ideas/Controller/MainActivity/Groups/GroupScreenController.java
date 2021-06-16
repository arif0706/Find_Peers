package com.example.ideas.Controller.MainActivity.Groups;

import android.content.Context;
import android.content.Intent;

import com.example.ideas.Controller.RetrofitClient;
import com.example.ideas.Model.Chat.Message;
import com.example.ideas.Model.Group;
import com.example.ideas.View.ChatScreen.ChatScreen;
import com.example.ideas.View.MainActivity.Groups.GroupScreenView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupScreenController implements  IGroupScreenController{

    GroupScreenView groupScreenView;

    public GroupScreenController(GroupScreenView groupScreenView){
        this.groupScreenView=groupScreenView;
    }





    @Override
    public void getLastMessage(String chat_id) {
        final Message[] message = new Message[1];
       Call<Message> messageCall= RetrofitClient.getInstance().getMyApi().getLastMessage(chat_id);
       messageCall.enqueue(new Callback<Message>() {
           @Override
           public void onResponse(Call<Message> call, Response<Message> response) {
               message[0] =response.body();



           }
           @Override
           public void onFailure(Call<Message> call, Throwable t) {

           }
       });
    }
}
