package com.example.ideas.View.PostFullView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ideas.Model.Post.Post;
import com.example.ideas.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;

public class PostFullScreen extends AppCompatActivity {

    TextView author,body,title;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_post_full_view);

        Intent intent=getIntent();

        Gson gson=new Gson();
        Post post=gson.fromJson(intent.getStringExtra("post"),Post.class);

        init();
        assignValues(post);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void assignValues(Post post) {

        title.setText(post.getTitle());
        body.setText(post.getText());
        author.setText(post.getUser().getFullname());
        toolbar.setTitle(post.getTitle());
    }

    void init(){
        author=findViewById(R.id.author);
        body=findViewById(R.id.body);
        title=findViewById(R.id.title);
        toolbar=findViewById(R.id.toolbar);
    }

}