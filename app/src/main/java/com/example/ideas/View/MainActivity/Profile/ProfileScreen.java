package com.example.ideas.View.MainActivity.Profile;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ideas.Controller.MainActivity.Profile.ProfileScreenController;
import com.example.ideas.Model.User;
import com.example.ideas.R;
import com.example.ideas.View.Authentication.AuthenticationScreen;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileScreen extends Fragment implements ProfileScreenView{

    private ProfileScreenViewModel mViewModel;
    View view;
    TextView username,no_of_posts;
    MaterialToolbar toolbar;


    String EDITING="EDIT";
    boolean isEditing;


    FirebaseUser user;


    ProfileScreenController profileScreenController;
    public static ProfileScreen newInstance() {
        return new ProfileScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.profile_screen_fragment, container, false);
         username=view.findViewById(R.id.username);
         no_of_posts=view.findViewById(R.id.no_of_posts);
         toolbar=view.findViewById(R.id.toolbar);


         user=FirebaseAuth.getInstance().getCurrentUser();

         profileScreenController=new ProfileScreenController(ProfileScreen.this);

         profileScreenController.getUser(user.getEmail());



         toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
             @Override
             public boolean onMenuItemClick(MenuItem item) {
                 switch (item.getItemId()){
                     case R.id.logout:
                         FirebaseAuth.getInstance().signOut();
                         Intent intent=new Intent(view.getContext(),AuthenticationScreen.class);
                         startActivity(intent);
                         getActivity().finish();
                         return true;
                 }
                 return false;
             }
         });
         return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(EDITING,isEditing);
    }

    @Override
    public void onGettingUser(User user){
            if (user == null) {
                Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_LONG).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show();
            } else {
                username.setText(user.getFullname());
                no_of_posts.setText(String.valueOf(user.getNo_of_posts()));
            }

        }
}