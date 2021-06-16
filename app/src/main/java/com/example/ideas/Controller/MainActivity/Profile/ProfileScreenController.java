package com.example.ideas.Controller.MainActivity.Profile;

import com.example.ideas.Controller.RetrofitClient;
import com.example.ideas.Model.User;
import com.example.ideas.View.MainActivity.Profile.ProfileScreenView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileScreenController implements IProfileScreenController {
    ProfileScreenView profileScreenView;

    public ProfileScreenController(ProfileScreenView profileScreenView){
        this.profileScreenView=profileScreenView;

    }

    @Override
    public void getUser(String email_id) {
        Call<User> getUser= RetrofitClient.getInstance().getMyApi().getUser(email_id);
        getUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user=response.body();
                profileScreenView.onGettingUser(user);

            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
