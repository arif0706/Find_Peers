package com.example.ideas.View.MainActivity.Groups.Request;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ideas.Adapter.RequestScreenAdapter;
import com.example.ideas.Controller.MainActivity.Groups.RequestScreenController;
import com.example.ideas.Model.Requests.Requests_list;
import com.example.ideas.Model.User;
import com.example.ideas.R;
import com.example.ideas.View.ProfilePreview.ProfilePreview;
import com.google.gson.Gson;

import java.util.List;


public class RequestScreen extends Fragment implements RequestScreenView, RequestScreenAdapter.RequestCardClickListener {


    User mongo_user;
    RecyclerView recyclerView;
    View view;

    RequestScreenController requestScreenController;
    RequestScreenAdapter requestScreenAdapter;



    public RequestScreen() {
        // Required empty public constructor
    }

    public RequestScreen(User mongo_user) {
        this.mongo_user=mongo_user;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_request_screen, container, false);
        initWidgets();
        requestScreenController=new RequestScreenController(this);


        requestScreenController.fetchRequests(mongo_user.get_id());
        


        return view;
    }


    void initWidgets(){

        recyclerView=view.findViewById(R.id.recycler_view);
    }

    @Override
    public void onFetchedRequests(List<Requests_list> requests_lists) {
        requestScreenAdapter=new RequestScreenAdapter(view.getContext(),requests_lists,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerView.setAdapter(requestScreenAdapter);

    }

    @Override
    public void onRequestClick(Requests_list requests_list) {
        Intent intent=new Intent(view.getContext(), ProfilePreview.class);
        intent.putExtra("user",new Gson().toJson(requests_list.getRequested_by()));
        intent.putExtra("group",new Gson().toJson(requests_list.getGroup_id()));
        intent.putExtra("request_id",requests_list.get_id());
        intent.putExtra("request_list",new Gson().toJson(requests_list));
        startActivity(intent);
    }
}