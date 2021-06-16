package com.example.ideas.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ideas.Model.User;
import com.example.ideas.View.MainActivity.Groups.Chat.ChatListScreen;
import com.example.ideas.View.MainActivity.Groups.Request.RequestScreen;

import org.jetbrains.annotations.NotNull;

public class GroupScreenViewPagerAdapter extends FragmentStateAdapter {

    int total_tabs;
    User mongo_user;
    public GroupScreenViewPagerAdapter(@NonNull @NotNull FragmentActivity fragmentActivity, int total_tabs, User mongo_user) {
        super(fragmentActivity);
        this.total_tabs=total_tabs;
        this.mongo_user=mongo_user;

    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new ChatListScreen(mongo_user);
            case 1:
                return new RequestScreen(mongo_user);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return total_tabs;
    }
}
