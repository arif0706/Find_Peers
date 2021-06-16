package com.example.ideas.View.MainActivity.Groups.Chat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ideas.Adapter.ChatListScreenAdapter;
import com.example.ideas.Controller.MainActivity.Groups.ChatListScreenController;
import com.example.ideas.Model.Group;
import com.example.ideas.Model.User;
import com.example.ideas.R;

import java.util.List;

public class ChatListScreen extends Fragment implements ChatListScreenView,ChatListScreenAdapter.OnGroupClickListener{

    View view;
    RecyclerView recyclerView;

    ChatListScreenController chatListScreenController;

    User mongo_user;

    ChatListScreenAdapter adapter;

    public ChatListScreen(){}


    public ChatListScreen(User mongo_user) {
        this.mongo_user=mongo_user;
    }

    public  ChatListScreen newInstance() {
        return new ChatListScreen();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_chat_list_screen, container, false);

        chatListScreenController=new ChatListScreenController(ChatListScreen.this);

        init();

        chatListScreenController.getGroups(mongo_user.get_id());
        return view;
    }

    private void init() {
        recyclerView=view.findViewById(R.id.recycler_view);



    }

    @Override
    public void onChatsFetched(List<Group> groupList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter=new ChatListScreenAdapter(view.getContext(),groupList,chatListScreenController,this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onGroupClick(String chat_id, String group_name) {
            chatListScreenController.onGroupClick(view.getContext(),chat_id,group_name);
    }
}