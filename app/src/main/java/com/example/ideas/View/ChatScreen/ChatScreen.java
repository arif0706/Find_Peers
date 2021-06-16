package com.example.ideas.View.ChatScreen;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideas.Adapter.ChatScreenAdapter;
import com.example.ideas.Controller.ChatScreen.ChatScreenController;
import com.example.ideas.Model.Chat.Message;
import com.example.ideas.Model.Chat.Typing;
import com.example.ideas.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ChatScreen extends AppCompatActivity implements ChatScreenView {

    MaterialToolbar toolbar;
    RecyclerView recyclerView;
    Button send;
    EditText message;

    ChatScreenAdapter chatScreenAdapter;

    ChatScreenController chatScreenController;


    List<Message> messageList =new ArrayList<>();;

    String chat_id;
    String group_name;

    FirebaseUser user;


    Typing typing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        init();
        chatScreenController=new ChatScreenController(this,this);;
        chat_id=getIntent().getStringExtra("chat_id");
        group_name=getIntent().getStringExtra("group_name");

        toolbar.setTitle(group_name);

        Message message =new Message();
        message.setRoomName(chat_id);
        user= FirebaseAuth.getInstance().getCurrentUser();
        message.setUserName(user.getEmail());


        typing=new Typing(chat_id,user.getEmail());
        chatScreenController.startChat(message);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setContent(ChatScreen.this.message.getText().toString());

                chatScreenController.sendMessage(message);
            }
        });



        chatScreenController.getMessages(chat_id);







    }

    private void init() {
        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recycler_view);
        send=findViewById(R.id.send);
        message=findViewById(R.id.message);
        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(!message.getText().toString().isEmpty())
                    chatScreenController.onTyping(typing);
                else
                    chatScreenController.onStoppedTyping(typing);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!message.getText().toString().isEmpty())
                    chatScreenController.onTyping(typing);
                else
                    chatScreenController.onStoppedTyping(typing);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!message.getText().toString().isEmpty())
                    chatScreenController.onTyping(typing);
                else
                    chatScreenController.onStoppedTyping(typing);

            }
        });




    }

    @Override
    public void onFetchChat(List<Message> list) {
        messageList=list;
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatScreen.this));
        chatScreenAdapter=new ChatScreenAdapter(ChatScreen.this, messageList,user);
        recyclerView.setAdapter(chatScreenAdapter);


    }

    @Override
    public void addMessage(Message message) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(message);
                chatScreenAdapter.notifyItemInserted(messageList.size());
                recyclerView.smoothScrollToPosition(messageList.size()-1);
            }
        });

    }

    @Override
    public void onTyping(String userName) {
       toolbar.setSubtitle(userName+" is typing...");
    }

    @Override
    public void onStoppedTyping() {
        toolbar.setSubtitle("");
    }


    @Override
    protected void onPause() {
        super.onPause();
        chatScreenController.onDisconnect();
    }
}