package com.example.ideas.View.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.example.ideas.Controller.Authentication.RegistrationController;
import com.example.ideas.Model.User;
import com.example.ideas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;

public class RegistrationScreen extends AppCompatActivity implements RegisterView{

    Button register;
    TextInputLayout email,password,full_name,skills;
    FirebaseAuth auth;
    RegistrationController controller;
    ChipGroup chipGroup;
    List<Integer> chip_ids;
    List<Integer> skills_chip_id;

    List<String> skills_string=new ArrayList<>();

    ChipGroup skills_chip_group;

    EditText editText;

    EditText bio;

    HorizontalScrollView horizontalScrollView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration_screen);
        initWidgets();
        controller=new RegistrationController(this);
        auth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_string=email.getEditText().getText().toString();
                String password_string=password.getEditText().getText().toString();
                String full_name_string=full_name.getEditText().getText().toString();
                String bio_string=bio.getText().toString();
                chip_ids=chipGroup.getCheckedChipIds();

                List<String> categories=new ArrayList<>();
                for(Integer id:chip_ids){
                    Chip chip=chipGroup.findViewById(id);
                    categories.add(chip.getText().toString());
                }


                if(!email_string.isEmpty() && !password_string.isEmpty()
                        && !full_name_string.isEmpty() && !bio_string.isEmpty()
                        && !skills_string.isEmpty() && !categories.isEmpty()) {
                    User user=new User();

                    user.setEmail(email_string);
                    user.setPassword(password_string);
                    user.setCategories(categories);
                    user.setFullname(full_name_string);
                    user.setBio(bio_string);
                    user.setSkills(skills_string);
                    getFCMToken(user);

                    Toast.makeText(RegistrationScreen.this, new Gson().toJson(user), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(RegistrationScreen.this, "Fill all the details", Toast.LENGTH_SHORT).show();

            }
        });
        final int[] id = {0};
        skills.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(skills_string.isEmpty()){
                    skills_chip_group.setVisibility(View.GONE);
                }
                if(!skills_string.contains(editText.getText().toString().toLowerCase())) {
                    skills_chip_group.setVisibility(View.VISIBLE);
                    Chip chip = new Chip(RegistrationScreen.this);
                    chip.setId(id[0]++);
                    chip.setLayoutParams(new ChipGroup.LayoutParams(ChipGroup.LayoutParams.WRAP_CONTENT, ChipGroup.LayoutParams.WRAP_CONTENT));
                    chip.setText(editText.getText().toString());
                    chip.setCloseIconResource(R.drawable.ic_baseline_remove_circle_outline_24);
                    chip.setCloseIconEnabled(true);

                    System.out.println("chip id" + chip.getId());

                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onClick(View v) {
                            skills_chip_group.removeView(chip);
                            skills_string.remove(chip.getText().toString());

                            if(skills_string.isEmpty()){
                                skills_chip_group.setVisibility(View.GONE);
                            }
                            else
                                skills_chip_group.setVisibility(View.VISIBLE);
                            System.out.println(skills_string);
                        }
                    });
                    skills_chip_group.addView(chip);
                    skills_string.add(editText.getText().toString().toLowerCase());

                    editText.setText("");


                    horizontalScrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                        }
                    });
                }
            }
        });

        bio.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId()==bio.getId()){
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });






    }

    private void getFCMToken(User user) {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<String> task) {
                user.setFcm_id_token(task.getResult());
                controller.onRegistration(user, auth);
            }
        });

    }

    private void initWidgets() {

        register=findViewById(R.id.register);
        email=findViewById(R.id.email);
        full_name=findViewById(R.id.full_name);
        password=findViewById(R.id.password);

        chipGroup=findViewById(R.id.chip_group);
        skills=findViewById(R.id.skills);

        editText=findViewById(R.id.edit_text);

        skills_chip_group=findViewById(R.id.skills_chip_group);
        horizontalScrollView=findViewById(R.id.horizontal_scroll);

        bio=findViewById(R.id.bio);




    }

    @Override
    public void onRegistrationSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegistrationFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}