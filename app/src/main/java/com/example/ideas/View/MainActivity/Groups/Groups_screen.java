package com.example.ideas.View.MainActivity.Groups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ideas.Adapter.GroupScreenViewPagerAdapter;
import com.example.ideas.Model.User;
import com.example.ideas.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.util.Objects;

public class Groups_screen extends Fragment {

    View view;

    ViewPager2 viewPager2;
    TabLayout tabLayout;

    GroupScreenViewPagerAdapter viewPagerAdapter;


    User mongo_user;
    public static Groups_screen newInstance() {
        return new Groups_screen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.groups_screen_fragment, container, false);

        init();

        viewPagerAdapter=new GroupScreenViewPagerAdapter(requireActivity(),tabLayout.getTabCount(),mongo_user);

        viewPager2.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout,viewPager2,true,true,(tab,position)->{
            switch (position){
                case 0:
                    tab.setText("Chats");
                    break;
                case 1:
                    tab.setText("Request");
                    break;
            }

        }).attach();


        return view;
    }

    private void init() {
        Gson gson=new Gson();
        viewPager2=view.findViewById(R.id.view_pager2);
        tabLayout=view.findViewById(R.id.tabLayout);

        Bundle bundle=getArguments();
        mongo_user=gson.fromJson(bundle.getString("mongo_user"),User.class);

    }




}