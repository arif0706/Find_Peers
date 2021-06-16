package com.example.ideas.Controller.Authentication;

import com.google.firebase.auth.FirebaseAuth;

public interface ILoginController {
    void onLogin(String email, String password);
    void onForgotPassword(String email);
}
