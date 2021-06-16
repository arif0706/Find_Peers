package com.example.ideas.View.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ideas.Controller.MainActivity.MainActivityController;
import com.example.ideas.Model.User;
import com.example.ideas.R;
import com.example.ideas.View.MainActivity.Groups.Groups_screen;
import com.example.ideas.View.MainActivity.Home.home_screen;
import com.example.ideas.View.MainActivity.Profile.ProfileScreen;
import com.example.ideas.View.MainActivity.UploadPost.upload_post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements MainActivityView{



    BottomNavigationView bottomNavigationView;
     String idToken;
    FirebaseUser user;
    MainActivityController mainActivityController;
    User mongo_user;
    Gson gson=new Gson();

    public static final String CHANNEL_ID="TinderForIdeas";
    public static final String CHANNEL_NAME="TinderForIdeas";
    public static final String CHANNEL_DESC="TinderForIdeas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNotification();
        initWidgets();
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        mainActivityController=new MainActivityController(this);
        getToken();
    }

    private void initNotification() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(CHANNEL_DESC);
            channel.enableLights(true);
            channel.setShowBadge(true);
            channel.setVibrationPattern(new long[0]);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    void getToken(){
        user= FirebaseAuth.getInstance().getCurrentUser();;
        assert user!=null;
        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<GetTokenResult> task) {

                idToken=task.getResult().getToken();

                mainActivityController.getUserDetails(user.getEmail());

            }
        });



    }
    void initWidgets(){

        bottomNavigationView=findViewById(R.id.bottom_navigation);

    }
   void openFragment(Fragment fragment){
        Bundle bundle=new Bundle();
        bundle.putString("id_token",idToken);
        bundle.putString("mongo_user",gson.toJson(mongo_user));
        fragment.setArguments(bundle);

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

   }
    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.home_page:
                    openFragment(home_screen.newInstance());
                    return true;
                case R.id.upload:
                    openFragment(upload_post.newInstance());
                    return true;
                case R.id.profile:
                    openFragment(ProfileScreen.newInstance());
                    return true;
                case R.id.chat:
                    openFragment(Groups_screen.newInstance());
                    return true;
            }
            return false;
        }
    };



    @Override
    public void onGetUserDetails(User user) {
        Toast.makeText(this, "on get user details", Toast.LENGTH_SHORT).show();
        mongo_user=user;
        openFragment(home_screen.newInstance());
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<String> task) {
                String token=task.getResult();
                User user=new User();
                user.setFcm_id_token(token);
                user.set_id(mongo_user.get_id());
                mainActivityController.UpdateUser(user);
            }
        });
    }

    @Override
    public void onUserUpdated(User user) {
        Toast.makeText(this, "Token Updated", Toast.LENGTH_SHORT).show();
    }
}
