package com.example.ideas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideas.Controller.MainActivity.Groups.ChatListScreenController;
import com.example.ideas.Controller.MainActivity.Groups.GroupScreenController;
import com.example.ideas.Model.Group;
import com.example.ideas.R;

import java.util.List;

public class ChatListScreenAdapter extends RecyclerView.Adapter<ChatListScreenAdapter.GroupViewHolder>{

    ChatListScreenController chatListScreenController;
    Context context;
    List<Group> groupList;
    View view;
    OnGroupClickListener groupClickListener;


    public ChatListScreenAdapter(Context context, List<Group> groupList, ChatListScreenController controller, OnGroupClickListener groupClickListener){
        this.context=context;
        this.chatListScreenController=controller;
        this.groupList=groupList;
        this.groupClickListener=groupClickListener;
    }


    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view= LayoutInflater.from(context).inflate(R.layout.group_card,parent,false);
        return new GroupViewHolder(view,groupClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {

        Group group=groupList.get(position);
        holder.title.setText(group.getGroup_name());
        if(group.getMessage()!=null) {
            holder.lastMessage.setText(group.getMessage().content);
            holder.username.setText(group.getMessage().userName.concat(":"));
        }
        else {
            holder.lastMessage.setText("No messages");
            holder.username.setVisibility(View.INVISIBLE);
        }




    }


    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        TextView lastMessage;
        TextView username;



        OnGroupClickListener groupClickListener;
        public GroupViewHolder(@NonNull View itemView,OnGroupClickListener groupClickListener) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            lastMessage=itemView.findViewById(R.id.lastMessage);
            username=itemView.findViewById(R.id.username);

            this.groupClickListener=groupClickListener;
            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
                groupClickListener.onGroupClick(groupList.get(getAdapterPosition()).getChat_id(),groupList.get(getAdapterPosition()).getGroup_name());
        }
    }

    public interface OnGroupClickListener{
        void onGroupClick(String chat_id,String group_name);
    }


}
