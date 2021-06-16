package com.example.ideas.Controller.MainActivity.Home;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.ideas.Controller.RetrofitClient;
import com.example.ideas.Model.Post.GetPost;
import com.example.ideas.Model.JoinGroup;
import com.example.ideas.Model.Post.React;
import com.example.ideas.Model.User;
import com.example.ideas.View.MainActivity.Home.HomeScreenView;
import com.example.ideas.View.MainActivity.Home.OptionsBottomSheet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenController implements IHomeScreenController, OptionsBottomSheet.OptionsBottomSheetListener {
    HomeScreenView homeScreenView;
    OptionsBottomSheet bottomSheet;
    public HomeScreenController(HomeScreenView homeScreenView){
        this.homeScreenView=homeScreenView;

    }
    @Override
    public void onJoinGroup(JoinGroup joinGroup) {

        Call<JoinGroup> userCall= RetrofitClient.getInstance().getMyApi().joinGroup(joinGroup);

        userCall.enqueue(new Callback<JoinGroup>() {
            @Override
            public void onResponse(Call<JoinGroup> call, Response<JoinGroup> response) {
                System.out.println("responseResponse"+response.body().getMessage());
                homeScreenView.onJoinedSuccess(response.message());

            }

            @Override
            public void onFailure(Call<JoinGroup> call, Throwable t) {
                System.out.println("responseFailure"+t.getMessage());

            }
        });

    }

    @Override
    public void getPosts(String email_id) {
        Call<List<GetPost>> call = RetrofitClient.getInstance().getMyApi().getIdeas(email_id);

        call.enqueue(new Callback<List<GetPost>>() {
            @Override
            public void onResponse(Call<List<GetPost>> call, Response<List<GetPost>> response) {
                System.out.println("posts"+response.body());
                List<GetPost> postList = response.body();
                homeScreenView.onGetPosts(postList);
            }
            @Override
            public void onFailure(Call<List<GetPost>> call, Throwable t) {
                System.out.println("error"+t.getLocalizedMessage());
            }
        });
    }









    @Override
    public void reactPost(React react) {
        Call<React> reactCall = RetrofitClient.getInstance().getMyApi().reactPost(react);
        reactCall.enqueue(new Callback<React>() {
            @Override
            public void onResponse(Call<React> call, Response<React> response) {

                System.out.println("react post" + response.message());

            }

            @Override
            public void onFailure(Call<React> call, Throwable t) {
                System.out.println("failed" + t.getMessage());
            }
        });
    }
        //BottomSheet

    @Override
    public void showOptionsBottomSheet(Context context,FragmentManager fragmentManager,GetPost post) {

        bottomSheet=new OptionsBottomSheet(context,HomeScreenController.this,post);
        bottomSheet.show(fragmentManager,"tag");


    }



    @Override
    public void onShareClick(String post_id) {
        String address="http://localhost:3000/posts/share/"+post_id;
        homeScreenView.onSharePost(address);
        bottomSheet.dismiss();
    }

    @Override
    public void onCancelClick() {

        bottomSheet.dismiss();
    }
}
