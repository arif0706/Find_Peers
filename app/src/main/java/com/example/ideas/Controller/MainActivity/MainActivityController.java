package com.example.ideas.Controller.MainActivity;

import com.example.ideas.Controller.RetrofitClient;
import com.example.ideas.Model.User;
import com.example.ideas.View.MainActivity.MainActivityView;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityController implements IMainActivityController{

    MainActivityView mainActivityView;

    public MainActivityController(MainActivityView mainActivityView){
        this.mainActivityView=mainActivityView;
    }

    @Override
    public void getUserDetails(String email_id) {

        Call<User> userCall = RetrofitClient.getInstance().getMyApi().getUser(email_id);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                mainActivityView.onGetUserDetails(response.body());
                System.out.println("in main activity controller"+new Gson().toJson(response.body()));

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("in main activity controller"+t.getMessage());
            }
        });
    }

    @Override
    public void UpdateUser(User user) {
        Call<User> userCall=RetrofitClient.getInstance().getMyApi().updateUser(user);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    mainActivityView.onUserUpdated(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
