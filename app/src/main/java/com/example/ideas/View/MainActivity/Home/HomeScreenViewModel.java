package com.example.ideas.View.MainActivity.Home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ideas.Model.Post.Post;

import java.util.List;

public class HomeScreenViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public MutableLiveData<List<Post>> postList;


    LiveData<List<Post>> getIdeaList(){
        if(postList==null) {
            postList = new MutableLiveData<>();
        }
        return postList;
    }

}