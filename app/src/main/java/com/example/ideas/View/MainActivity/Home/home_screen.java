package com.example.ideas.View.MainActivity.Home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ideas.Adapter.HomeScreenAdapter;
import com.example.ideas.Controller.MainActivity.Home.HomeScreenController;
import com.example.ideas.Model.Post.GetPost;
import com.example.ideas.Model.Post.Post;
import com.example.ideas.Model.User;
import com.example.ideas.R;
import com.example.ideas.View.PostFullView.PostFullScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class home_screen extends Fragment implements HomeScreenView, HomeScreenAdapter.PostClickListener {

    private HomeScreenViewModel mViewModel;
    View view;
    RecyclerView recyclerView;
    HomeScreenAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    HomeScreenController homeScreenController;
    FirebaseUser user;

    String mongo_user_id;

    String id_token;

    public static home_screen newInstance() {
        return new home_screen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        view= inflater.inflate(R.layout.home_screen_fragment, container, false);
        recyclerView=view.findViewById(R.id.recycler_view);
        swipeRefreshLayout=view.findViewById(R.id.refresh_layout);
        homeScreenController=new HomeScreenController(home_screen.this);

        user= FirebaseAuth.getInstance().getCurrentUser();
        getToken();



        homeScreenController.getPosts(mongo_user_id);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                homeScreenController.getPosts(mongo_user_id);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    private void getToken() {
        Gson gson=new Gson();
        Bundle bundle=getArguments();
        id_token=bundle.getString("id_token");
        User mongo_user=gson.fromJson(bundle.getString("mongo_user"),User.class);
        mongo_user_id=mongo_user.get_id();
       FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
           @Override
           public void onComplete(@NonNull @NotNull Task<String> task) {
               Toast.makeText(view.getContext(), task.getResult(), Toast.LENGTH_SHORT).show();


               System.out.println("token "+task.getResult());
           }
       });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(HomeScreenViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onJoinedSuccess(String group_name) {
        Toast.makeText(view.getContext(), group_name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetPosts(List<GetPost> postList) {
        Gson gson=new Gson();
        System.out.println(gson.toJson(postList));;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       adapter=new HomeScreenAdapter(getContext(),postList,homeScreenController,home_screen.this,id_token,mongo_user_id);
        recyclerView.setAdapter(adapter);





    }
    @Override
    public void onGetUserDetails(User user) {

        mongo_user_id=user.get_id();
        homeScreenController.getPosts(mongo_user_id);

    }

    @Override
    public void onSharePost(String address) {

        Toast.makeText(view.getContext(), address, Toast.LENGTH_SHORT).show();
        System.out.println("address "+address);
    }

    @Override
    public void onPostClick(Post post,View view,String transition_name) {

        Gson gson=new Gson();
        Intent intent=new Intent(home_screen.this.view.getContext(), PostFullScreen.class);

        Pair<View,String> toolbar=Pair.create(this.view.findViewById(R.id.toolbar),ViewCompat.getTransitionName(this.view.findViewById(R.id.toolbar)));
        Pair<View,String> card=Pair.create(view,transition_name);
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(getActivity(),toolbar,card);

        intent.putExtra("post", gson.toJson(post));
        startActivity(intent,options.toBundle());
    }
}