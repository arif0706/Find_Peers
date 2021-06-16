package com.example.ideas.Controller.ProfilePreview;

import com.example.ideas.Controller.RetrofitClient;
import com.example.ideas.Model.Post.Post;
import com.example.ideas.Model.Requests.Request;
import com.example.ideas.Model.Requests.Requests_list;
import com.example.ideas.Model.User;
import com.example.ideas.View.ProfilePreview.ProfilePreviewView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePreviewController implements IProfilePreviewController{

    ProfilePreviewView profilePreviewView;
    public ProfilePreviewController(ProfilePreviewView profilePreviewView){
        this.profilePreviewView=profilePreviewView;
    }
    @Override
    public void getUserDetails(User user) {

        Call<User> userCall= RetrofitClient.getInstance().getMyApi().getUserById(user.get_id());
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("response"+response.body());
                profilePreviewView.onGetUserDetails(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("getUserDetails"+t.getMessage());
            }
        });
    }

    @Override
    public void getUserPosts(String user_id) {

        Call<List<Post>> call=RetrofitClient.getInstance().getMyApi().getUserPosts(user_id);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                System.out.println("getUserPostsSuccess"+response.body());
                profilePreviewView.onGetUserPosts(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                System.out.println("getUserPostsError"+t.getMessage());
            }
        });
    }

    @Override
    public void onAcceptClicked(String request_id) {
        Call<Requests_list> requestCall= RetrofitClient.getInstance().getMyApi().acceptRequest(request_id);
        requestCall.enqueue(new Callback<Requests_list>() {
            @Override
            public void onResponse(Call<Requests_list> call, Response<Requests_list> response) {

            }

            @Override
            public void onFailure(Call<Requests_list> call, Throwable t) {

            }
        });

    }

    @Override
    public void onRejectClicked() {


    }
}
