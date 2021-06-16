package com.example.ideas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideas.Model.Chat.Message;
import com.example.ideas.R;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ChatScreenAdapter extends RecyclerView.Adapter<ChatScreenAdapter.ChatViewHolder> {

    Context context;
    List<Message> messageList;

    View view;
    FirebaseUser user;

    public ChatScreenAdapter(Context context, List<Message> messageList, FirebaseUser user){
        this.messageList = messageList;
        this.context=context;
        this.user=user;
    }
    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.chat_card,parent,false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.message.setText(message.getContent());
        if(!user.getEmail().equals(message.getUserName()))
            holder.sender.setText(message.getUserName());
        else
            holder.sender.setText("You");

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView sender;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            message=itemView.findViewById(R.id.message);
            sender=itemView.findViewById(R.id.sender);
        }
    }
}
