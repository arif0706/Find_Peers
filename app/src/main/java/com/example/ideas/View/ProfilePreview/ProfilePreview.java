package com.example.ideas.View.ProfilePreview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ideas.Adapter.ProfilePreviewAdapter;
import com.example.ideas.Controller.ProfilePreview.ProfilePreviewController;
import com.example.ideas.Model.Group;
import com.example.ideas.Model.Post.Post;
import com.example.ideas.Model.Requests.Requests_list;
import com.example.ideas.Model.User;
import com.example.ideas.R;
import com.example.ideas.View.PostFullView.PostFullScreen;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;

import java.util.List;

public class ProfilePreview extends AppCompatActivity implements ProfilePreviewView,ProfilePreviewAdapter.PostCardClickListener{

    MaterialToolbar toolbar;

    ProfilePreviewController controller;

    TextView email,fullname,skills,bio;

    RecyclerView recyclerView;

    Button accept,reject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_preview);

        initWidgets();
        Intent intent=getIntent();
        User user=new Gson().fromJson(intent.getStringExtra("user"),User.class);
        Group group=new Gson().fromJson(intent.getStringExtra("group"),Group.class);
        String request_id=intent.getStringExtra("request_id");

        Requests_list requests_list=new Gson().fromJson(intent.getStringExtra("request_list"),Requests_list.class);
        toolbar.setTitle(user.getFullname());

        Toast.makeText(this, new Gson().toJson(user), Toast.LENGTH_SHORT).show();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        controller=new ProfilePreviewController(this);

        controller.getUserDetails(user);


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ProfilePreview.this, request_id, Toast.LENGTH_SHORT).show();
                controller.onAcceptClicked(request_id);
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void initWidgets() {

        toolbar=findViewById(R.id.toolbar);

        email=findViewById(R.id.email);
        fullname=findViewById(R.id.username);
        skills=findViewById(R.id.skills);
        bio=findViewById(R.id.bio);

        accept=findViewById(R.id.accept);
        reject=findViewById(R.id.reject);

        recyclerView=findViewById(R.id.recycler_view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onGetUserDetails(User user) {
        controller.getUserPosts(user.get_id());

        setAllTextViews(user);

    }

    @Override
    public void onGetUserPosts(List<Post> posts) {
        ProfilePreviewAdapter profilePreviewAdapter=new ProfilePreviewAdapter(ProfilePreview.this,posts,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(profilePreviewAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setAllTextViews(User user) {
        email.setText(user.getEmail());

        fullname.setText(user.getFullname());

        bio.setText(user.getBio());
        int i=0;
        for (String skill:
                user.getSkills()
             ) {
            skills.append((i+1)+". "+skill+".\n");
            i++;
        }
    }

    @Override
    public void onPostClick(Post post) {

        System.out.println("post"+new Gson().toJson(post));

        System.out.println("post full name"+post.getUser().getFullname());
        Intent intent=new Intent(this, PostFullScreen.class);
        intent.putExtra("post",new Gson().toJson(post));
        startActivity(intent);
    }
}