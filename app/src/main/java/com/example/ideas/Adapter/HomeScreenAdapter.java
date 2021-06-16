package com.example.ideas.Adapter;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideas.Controller.MainActivity.Home.HomeScreenController;
import com.example.ideas.Model.Post.GetPost;
import com.example.ideas.Model.JoinGroup;
import com.example.ideas.Model.Post.Post;
import com.example.ideas.Model.Post.React;
import com.example.ideas.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.PostHolder> {
    Context context;
    List<GetPost> PostList;
    View view;
    HomeScreenController homeScreenController;
    FirebaseUser user;

    PostClickListener postClickListener;

    String id_token;
    String mongo_user_id;

    final ValueAnimator[] likeButton = {null};
    final ValueAnimator[] dislikeButton = {null};
    final ColorStateList checked_color=ColorStateList.valueOf(Color.parseColor("#019FEE"));
    final ColorStateList unChecked_color=ColorStateList.valueOf(Color.GRAY);
    public HomeScreenAdapter(Context context, List<GetPost> PostList, HomeScreenController homeScreenController, PostClickListener postClickListener, String idToken, String mongo_user_id) {
        this.context = context;
        this.PostList = PostList;
        this.homeScreenController=homeScreenController;
        this.postClickListener=postClickListener;
        this.id_token=idToken;
        this.mongo_user_id=mongo_user_id;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view=LayoutInflater.from(context).inflate(R.layout.post_card,parent,false);
        user=FirebaseAuth.getInstance().getCurrentUser();

        return new PostHolder(view,postClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        GetPost post=PostList.get(position);
        holder.title.setText(post.getPost().getTitle());
        holder.body.setText(post.getPost().getText());
        holder.author.setText(post.getPost().getUser().getFullname());



        holder.join.setEnabled(!post.isJoined());

        holder.like.setText(String.valueOf(post.getPost().getLikes().getNo_of_likes()));
        holder.dislike.setText(String.valueOf(post.getPost().getDislikes().getNo_of_dislikes()));

        setReact(holder,post);
        JoinGroup joinGroup = new JoinGroup();
        if(post.getPost().getGroup()==null)
            holder.join.setVisibility(View.INVISIBLE);
        else {
            holder.join.setVisibility(View.VISIBLE);

            joinGroup.setGroup_id(post.getPost().getGroup().get_id());
            joinGroup.setMongo_user_id(mongo_user_id);
        }
        holder.join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeScreenController.onJoinGroup(joinGroup);
            }
        });

        holder.options_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeScreenController.showOptionsBottomSheet(context,((AppCompatActivity) context).getSupportFragmentManager(),post);
            }
        });


        holder.like.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               React react=checkLikedStatus(holder);
                react.setPost_id(post.getPost().get_id());
                react.setIdToken(id_token);
                react.setUser_id(mongo_user_id);

                homeScreenController.reactPost(react);
            }
        });

        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                React react=checkDislikedStatus(holder);
                react.setPost_id(post.getPost().get_id());
                react.setIdToken(id_token);
                react.setUser_id(mongo_user_id);

                homeScreenController.reactPost(react);
            }
        });

    }

    private void setReact(PostHolder holder, GetPost post) {

        if(post.isLiked()){
            holder.like.setTextColor(checked_color);
            holder.like.setIconTint(checked_color);
            likeButton[0] =ValueAnimator.ofArgb(Color.GREEN, Color.RED, Color.BLUE);
        }
        if(post.isDisliked()){
            holder.dislike.setTextColor(checked_color);
            holder.dislike.setIconTint(checked_color);
            dislikeButton[0]=ValueAnimator.ofArgb(Color.GREEN,Color.RED,Color.BLUE);
        }
    }

    React checkLikedStatus(PostHolder holder){

        React react=new React();
        if(likeButton[0] !=null) {
            holder.like.setTextColor(unChecked_color);
            holder.like.setIconTint(unChecked_color);
            likeButton[0] = null;
            react.setLike(false);
        }
        else {
            react.setLike(true);
            react.setDislike(false);
            likeButton[0] =ValueAnimator.ofArgb(Color.GREEN, Color.RED, Color.BLUE);
            likeButton[0].addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    holder.like.setIconTint(checked_color);
                    holder.like.setTextColor(checked_color);
                    if(dislikeButton[0]!=null){
                        holder.dislike.setIconTint(unChecked_color);
                        holder.dislike.setTextColor(unChecked_color);
                        dislikeButton[0]=null;
                    }
                }
            });
            likeButton[0].start();
        }
        return react;

    }
    React checkDislikedStatus(PostHolder holder){
        React react=new React();
        if(dislikeButton[0] !=null){
            holder.dislike.setIconTint(unChecked_color);
            holder.dislike.setTextColor(unChecked_color);
            dislikeButton[0] =null;
            react.setDislike(false);
        }
        else{
            react.setDislike(true);
            react.setLike(false);
            dislikeButton[0] =ValueAnimator.ofArgb(Color.GREEN,Color.RED,Color.BLUE);
            dislikeButton[0].addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    holder.dislike.setTextColor(checked_color);
                    holder.dislike.setIconTint(checked_color);
                    if(likeButton[0]!=null){
                        holder.like.setTextColor(unChecked_color);
                        holder.like.setIconTint(unChecked_color);
                        likeButton[0]=null;
                    }
                }
            });
            dislikeButton[0].start();;
        }
        return react;
    }

    public interface PostClickListener{
        void onPostClick(Post post_id,View view,String transition_name);
    }

    @Override
    public int getItemCount() {
        return PostList.size();
    }


    public class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title,author;
        TextView body;
        Button join;
        PostClickListener postClickListener;

        ImageButton options_menu;
        ConstraintLayout post_click;

        MaterialButton like,dislike;

        public PostHolder(@NonNull View itemView, PostClickListener postClickListener) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            body=itemView.findViewById(R.id.body);
            author=itemView.findViewById(R.id.author);
            join=itemView.findViewById(R.id.join);
            options_menu=itemView.findViewById(R.id.options_menu);
            post_click=itemView.findViewById(R.id.post_click);
            like=itemView.findViewById(R.id.like);
            dislike=itemView.findViewById(R.id.dislike);

            this.postClickListener=postClickListener;
            post_click.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            postClickListener.onPostClick(PostList.get(getAdapterPosition()).getPost(),itemView, ViewCompat.getTransitionName(itemView));

        }
    }
}
