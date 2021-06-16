package com.example.ideas.Controller.MainActivity.UploadPost;

import com.example.ideas.Controller.RetrofitClient;
import com.example.ideas.Model.Post.Post;
import com.example.ideas.View.MainActivity.UploadPost.UploadPostView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadPostController implements IUploadPostController {

    UploadPostView uploadPostView;
    public UploadPostController(UploadPostView uploadPostView){
        this.uploadPostView=uploadPostView;

    }
    @Override
    public void onCreation(Post post) {
        System.out.println(post);
        Call<Post> postIdea= RetrofitClient.getInstance().getMyApi().createPost(post);
        postIdea.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    uploadPostView.onSuccess(String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                    uploadPostView.onFailure(t.getMessage().toString());
            }
        });

    }
}
