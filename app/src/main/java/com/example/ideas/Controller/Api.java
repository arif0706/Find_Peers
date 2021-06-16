package com.example.ideas.Controller;


import com.example.ideas.Model.Chat.Message;
import com.example.ideas.Model.Post.GetPost;
import com.example.ideas.Model.Group;
import com.example.ideas.Model.JoinGroup;
import com.example.ideas.Model.Post.Post;
import com.example.ideas.Model.Post.React;
import com.example.ideas.Model.Requests.Request;
import com.example.ideas.Model.Requests.Requests_list;
import com.example.ideas.Model.User;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL="http://10.0.2.2:8080/";


    // GET Requests
    @GET("posts/{id}")
    Call<List<GetPost>> getIdeas(@Path("id") String email_id);

    @GET("group/{id}")
    Call<List<Group>> getGroups(@Path("id") String id);

    @GET("user/{email}")
    Call<User> getUser(@Path("email") String email_id);

    @GET("group/requests/{user_id}")
    Call<List<Requests_list>> getRequests(@Path("user_id") String user_id);

    @GET("chat/{chat_id}")
    Call<List<Message>> getChat(@Path("chat_id") String chat_id);

    @GET("chat/lastMessage/{chat_id}")
    Call<Message> getLastMessage(@Path("chat_id") String chat_id);

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") String post_id);

    @GET("user/id/{id}")
    Call<User> getUserById(@Path("id") String user_id);

    @GET("posts/user_posts/{user_id}")
    Call<List<Post>> getUserPosts(@Path("user_id")String user_id);


    //POST requests
    @POST("postCreation")
    Call<Post> createPost(@Body Post post);

    @POST("user/register")
    Call<User> createUser(@Body User user);


    //PUT requests

    @PUT("group/request")
    Call<JoinGroup> joinGroup(@Body JoinGroup joinGroup);

    @PUT("postCreation/react")
    Call<React> reactPost( @Body  React react);

    @PUT("user/update")
    Call<User> updateUser(@Body User user);

    @PUT("group/accept_request/{request_id}")
    Call<Requests_list> acceptRequest(@Path("request_id") String request_id);

}
