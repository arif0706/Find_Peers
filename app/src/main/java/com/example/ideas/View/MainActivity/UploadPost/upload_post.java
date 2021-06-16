package com.example.ideas.View.MainActivity.UploadPost;

import androidx.constraintlayout.motion.widget.TransitionBuilder;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ideas.Controller.MainActivity.UploadPost.UploadPostController;
import com.example.ideas.Model.Group;
import com.example.ideas.Model.Post.Dislikes;
import com.example.ideas.Model.Post.Likes;
import com.example.ideas.Model.Post.Post;
import com.example.ideas.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class upload_post extends Fragment implements UploadPostView{

    private UploadPostViewModel mViewModel;
    View view;
    EditText title,body;

    Button submit;
    UploadPostController controller;
    FirebaseAuth auth;
    FirebaseUser user;
    String IdToken;
    ConstraintLayout constraintLayout,group_creation_layout;
    RadioGroup radioGroup;
    NestedScrollView nestedScrollView;
    ChipGroup chipGroup;


    boolean group_creation=false;
    EditText group_name,no_of_group_members;

    public static upload_post newInstance() {
        return new upload_post();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.upload_post_fragment, container, false);
        initialize();


        controller=new UploadPostController(upload_post.this);


        getToken();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Post post=setPostFunction();
                controller.onCreation(post);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TransitionManager.beginDelayedTransition(group_creation_layout,new AutoTransition());

                switch (checkedId){
                    case R.id.yes:
                        nestedScrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                nestedScrollView.fullScroll(View.FOCUS_DOWN);
                            }
                        });
                        group_creation=true;
                        group_creation_layout.setVisibility(View.VISIBLE);
                        break;
                    case R.id.no:
                        nestedScrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                nestedScrollView.fullScroll(View.FOCUS_UP);
                            }
                        });
                        group_creation=false;
                        group_creation_layout.setVisibility(View.GONE);
                        break;
                }

            }
        });



        keyboardVisibility();
        return view;
    }

    Post setPostFunction(){

        Post post=new Post();
        post.setText(body.getText().toString());
        post.setAuthor_id(user.getEmail());
        post.setTitle(title.getText().toString());
        post.setDate(new Date().toString());
        post.setIdToken(IdToken);

        Likes likes=new Likes();
        likes.setNo_of_likes(0);
        likes.setLikers(new ArrayList<>());
        post.setLikes(likes);

        Dislikes dislikes=new Dislikes();
        dislikes.setNo_of_dislikes(0);
        dislikes.setDislikers(new ArrayList<>());
        post.setDislikes(dislikes);


        List<String> categories=new ArrayList<>();
        List<Integer> chip_ids=chipGroup.getCheckedChipIds();
        for(Integer id:chip_ids){
            Chip chip=chipGroup.findViewById(id);
            categories.add(chip.getText().toString());
        }

        post.setCategories(categories);


        if(group_creation) {
            Group group = new Group();
            group.setGroup_name(group_name.getText().toString());

            post.setGroup(group);
        }



        return post;
    }

    private void keyboardVisibility() {
        constraintLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                constraintLayout.getWindowVisibleDisplayFrame(r);
                int screenHeight = constraintLayout.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;
                if (keypadHeight > screenHeight * 0.15) {
                    hideBottomNavigation(true);
                } else {
                    hideBottomNavigation(false);
                }
            }
        });


    }

    private void hideBottomNavigation(boolean hide) {
        BottomNavigationView view=getActivity().findViewById(R.id.bottom_navigation);

        if(hide){
            view.setVisibility(View.GONE);
        }
        else{
            view.setVisibility(View.VISIBLE);
        }
    }

    private void initialize() {
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        title=view.findViewById(R.id.title);
        body=view.findViewById(R.id.body);
        submit=view.findViewById(R.id.submit);
        group_name=view.findViewById(R.id.group_name);

        constraintLayout=view.findViewById(R.id.constraint_layout);

        chipGroup=view.findViewById(R.id.chip_group);

        radioGroup=view.findViewById(R.id.radioGroup);

        group_creation_layout=view.findViewById(R.id.group_details_layout);

        nestedScrollView=view.findViewById(R.id.nested_scroll_view);
    }

    private void getToken() {
        Bundle bundle=getArguments();
        IdToken=bundle.getString("id_token");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UploadPostViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}