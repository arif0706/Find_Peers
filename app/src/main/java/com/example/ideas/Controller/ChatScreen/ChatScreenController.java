package com.example.ideas.Controller.ChatScreen;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.ideas.Controller.ConnectSocket;
import com.example.ideas.Controller.RetrofitClient;
import com.example.ideas.Model.Chat.Message;
import com.example.ideas.Model.Chat.Typing;
import com.example.ideas.View.ChatScreen.ChatScreenView;
import com.google.gson.Gson;

import java.util.List;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatScreenController implements IChatScreenController{

    Socket socket;
    Context context;

    Message message;
    Gson gson=new Gson();

    ChatScreenView chatScreenView;

   public ChatScreenController(Context context,ChatScreenView chatScreenView){
        this.context=context;
        this.chatScreenView=chatScreenView;

    }

    @Override
    public void startChat(Message message) {
        this.message = message;
        ConnectSocket connectSocket=new ConnectSocket();

        socket=connectSocket.getSocket();
        socket.connect();

        socket.on(Socket.EVENT_CONNECT,onConnect);
        socket.on("updateChat",onUpdate);
        socket.on("typing",onTyping);
        socket.on("stoppedTyping",onStoppedTyping);

    }

    @Override
    public void sendMessage(Message message) {
       socket.emit("newMessage",gson.toJson(message));
       chatScreenView.addMessage(message);
    }

    @Override
    public void onDisconnect() {
        socket.disconnect();
    }

    @Override
    public void getMessages(String chat_id) {
      Call<List<Message>> messageCall=RetrofitClient.getInstance().getMyApi().getChat(chat_id);
      messageCall.enqueue(new Callback<List<Message>>() {
          @Override
          public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
              List<Message> messageList=response.body();
              chatScreenView.onFetchChat(messageList);
          }

          @Override
          public void onFailure(Call<List<Message>> call, Throwable t) {

          }
      });



    }

    @Override
    public void onTyping(Typing typing) {

        socket.emit("onTyping",gson.toJson(typing));
    }

    @Override
    public void onStoppedTyping(Typing typing) {
        socket.emit("onStoppedTyping",gson.toJson(typing));
    }


    private final Emitter.Listener onUpdate=new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    System.out.println("UpdateChat"+args[0].toString());
                    Message message =gson.fromJson(args[0].toString(), Message.class);

                    chatScreenView.addMessage(message);
                }
            });
        }
    };

    private final Emitter.Listener onConnect=new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                        socket.emit("subscribe",gson.toJson(message));
                }
            });
        }
    };
    private final Emitter.Listener onTyping=new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    chatScreenView.onTyping(args[0].toString());
                }
            });
        }
    };
    private final Emitter.Listener onStoppedTyping=new Emitter.Listener() {
        @Override
        public void call(Object... args) {
           new Handler(Looper.getMainLooper()).post(new Runnable() {
               @Override
               public void run() {
                chatScreenView.onStoppedTyping();
               }
           });
        }
    };
}
