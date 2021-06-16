package com.example.ideas.Controller.Authentication;

import com.example.ideas.Model.User;
import com.google.firebase.auth.FirebaseAuth;

public interface IRegistrationController{
    void onRegistration(User user,FirebaseAuth auth);

}
