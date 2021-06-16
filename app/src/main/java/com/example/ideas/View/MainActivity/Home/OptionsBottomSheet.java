package com.example.ideas.View.MainActivity.Home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ideas.Model.Post.GetPost;
import com.example.ideas.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

public class OptionsBottomSheet extends BottomSheetDialogFragment {

    View root;
    Context context;
    GetPost post;
    OptionsBottomSheetListener optionsBottomSheetListener;



    Button share,cancel;

    public OptionsBottomSheet(Context context, OptionsBottomSheetListener optionsBottomSheetListener, GetPost post){
        this.optionsBottomSheetListener=optionsBottomSheetListener;
        this.context=context;
        this.post=post;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        root=LayoutInflater.from(context).inflate(R.layout.home_options_bottom_sheet,container,false);
        init();
        return root;
    }

    void init(){

        share=root.findViewById(R.id.share);
        cancel=root.findViewById(R.id.cancel);


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsBottomSheetListener.onShareClick(post.getPost().get_id());
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsBottomSheetListener.onCancelClick();
            }
        });

    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
    }


    public interface OptionsBottomSheetListener{
        void onShareClick(String post_id);
        void onCancelClick();
    }
}
