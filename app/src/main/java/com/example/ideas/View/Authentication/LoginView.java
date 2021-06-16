package com.example.ideas.View.Authentication;

public interface LoginView {

    void onLoginSuccess(String message);
    void onLoginError(String message);
    void onPasswordReset(String message);
}
