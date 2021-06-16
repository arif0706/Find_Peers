package com.example.ideas.Controller.MainActivity.Groups;

import com.example.ideas.Controller.RetrofitClient;
import com.example.ideas.Model.Requests.Requests_list;
import com.example.ideas.View.MainActivity.Groups.Request.RequestScreenView;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestScreenController implements IRequestScreenController{

    RequestScreenView requestScreenView;
    public RequestScreenController(RequestScreenView requestScreenView){
        this.requestScreenView=requestScreenView;

    }

    @Override
    public void fetchRequests(String mongo_user_id) {

        Call<List<Requests_list>> call= RetrofitClient.getInstance().getMyApi().getRequests(mongo_user_id);
        call.enqueue(new Callback<List<Requests_list>>() {
            @Override
            public void onResponse(Call<List<Requests_list>> call, Response<List<Requests_list>> response) {

                        System.out.println("requests response"+new Gson().toJson(response.body()));
                        requestScreenView.onFetchedRequests(response.body());
            }

            @Override
            public void onFailure(Call<List<Requests_list>> call, Throwable t) {
                    System.out.println("error"+t.getMessage());
            }
        });
    }
}
