package com.example.ideas.View.MainActivity.Profile;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ideas.Controller.RetrofitClient;
import com.example.ideas.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileScreenViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public MutableLiveData<User> user_result;

    LiveData<User> getUser(String email_id){
        if(user_result==null)
            user_result=new MutableLiveData<>();
        loadUser(email_id);
        return user_result;
    }

    void loadUser(String email_id){

    }


}