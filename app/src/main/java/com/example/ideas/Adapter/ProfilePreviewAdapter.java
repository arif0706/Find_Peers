package com.example.ideas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ideas.Model.Post.Post;
import com.example.ideas.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProfilePreviewAdapter extends RecyclerView.Adapter<ProfilePreviewAdapter.PostCardHolder> {

    Context context;
    List<Post> posts;
    View view;
    PostCardClickListener postCardClickListener;

    public ProfilePreviewAdapter(Context context,List<Post> posts,PostCardClickListener postCardClickListener){
        this.context=context;
        this.posts=posts;
        this.postCardClickListener=postCardClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public PostCardHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        view= LayoutInflater.from(context).inflate(R.layout.profile_preview_post_card,parent,false);
        return new PostCardHolder(view,postCardClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PostCardHolder holder, int position) {
        Post post=posts.get(position);

        holder.title.setText(post.getTitle());
        holder.likes.append(String.valueOf(post.getLikes().getNo_of_likes()));
        holder.dislikes.append(String.valueOf(post.getDislikes().getNo_of_dislikes()));

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostCardHolder extends RecyclerView.ViewHolder {
        TextView title,likes,dislikes;


        PostCardClickListener postCardClickListener;
        public PostCardHolder(@NonNull @NotNull View itemView,PostCardClickListener postCardClickListener) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            likes=itemView.findViewById(R.id.likes);
            dislikes=itemView.findViewById(R.id.dislikes);
            this.postCardClickListener=postCardClickListener;


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Post post=posts.get(getAdapterPosition());
                    postCardClickListener.onPostClick(post);
                }
            });

        }
    }
    public interface PostCardClickListener{
        void onPostClick(Post post);
    }
}
