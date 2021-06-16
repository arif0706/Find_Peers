package com.example.ideas.Controller.Authentication;

import androidx.annotation.NonNull;

import com.example.ideas.Controller.RetrofitClient;
import com.example.ideas.Model.User;
import com.example.ideas.View.Authentication.RegisterView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationController implements IRegistrationController {

    RegisterView registerView;
    public RegistrationController(RegisterView registerView){
        this.registerView=registerView;
    }
    @Override
    public void onRegistration(User user, FirebaseAuth auth) {

        auth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser firebaseUser=auth.getCurrentUser();
                            UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder().setDisplayName(user.getFullname()).build();
                            firebaseUser.updateProfile(changeRequest);
                            User user1=new User();
                            user1.setEmail(user.getEmail());
                            user1.setFullname(user.getFullname());
                            user1.setNo_of_posts(0);
                            user1.setCategories(user.getCategories());
                            user1.setFcm_id_token(user.getFcm_id_token());
                            user1.setBio(user.getBio());
                            user1.setSkills(user.getSkills());

                            Call<User> postUser= RetrofitClient.getInstance().getMyApi().createUser(user1);
                            postUser.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    registerView.onRegistrationSuccess("Registration success");
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    registerView.onRegistrationFailure("Registration failed");
                                }
                            });

                        }
                        else{
                            registerView.onRegistrationFailure("Registration failed");
                        }
                    }
                });

    }
}
