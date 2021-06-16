package com.example.ideas.View.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ideas.Controller.Authentication.ILoginController;
import com.example.ideas.Controller.Authentication.LoginController;
import com.example.ideas.R;
import com.example.ideas.View.MainActivity.MainActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationScreen extends AppCompatActivity implements LoginView {
    FirebaseAuth auth;
    TextInputLayout email,password;
    Button login,register,forgot_password;

    ILoginController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_authentication_screen);

        auth=FirebaseAuth.getInstance();
        initWidgets();

        controller=new LoginController(this,auth);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.getEditText().getText().toString().isEmpty()&&!password.getEditText().getText().toString().isEmpty())
                    controller.onLogin(email.getEditText().getText().toString(),password.getEditText().getText().toString());
                else
                    Toast.makeText(AuthenticationScreen.this, "Fill in the credentials", Toast.LENGTH_SHORT).show();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AuthenticationScreen.this,RegistrationScreen.class);
                startActivity(intent);
            }
        });
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.getEditText().getText().toString().isEmpty())
                    controller.onForgotPassword(email.getEditText().getText().toString());
                else
                    Toast.makeText(AuthenticationScreen.this, "Give the email id", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initWidgets() {
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        forgot_password=findViewById(R.id.forgot_password);

    }

    @Override
    public void onLoginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=auth.getCurrentUser();
        if(user!=null){
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    public void onLoginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordReset(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}