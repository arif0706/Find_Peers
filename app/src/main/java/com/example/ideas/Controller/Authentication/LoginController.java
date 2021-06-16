package com.example.ideas.Controller.Authentication;

import androidx.annotation.NonNull;

import com.example.ideas.Model.User;
import com.example.ideas.View.Authentication.LoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginController implements ILoginController{

    LoginView loginView;
    FirebaseAuth auth;
    public LoginController(LoginView loginView,FirebaseAuth auth){
        this.loginView=loginView;
        this.auth=auth;
    }
    @Override
    public void onLogin(String email, String password) {
            User user=new User();
            user.setEmail(email);
            user.setPassword(password);

            auth.signInWithEmailAndPassword(user.getEmail(),user.getPassword())
                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                loginView.onLoginSuccess("Logged in");
                            }
                            else{
                                loginView.onLoginError("Invalid Credentials");
                            }
                       }
                   });
    }

    @Override
    public void onForgotPassword(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            loginView.onPasswordReset("A password reset mail has been sent to your given email");
                        }
                        else{
                            loginView.onPasswordReset(task.getException().getMessage());
                        }
                    }
                });
    }
}
