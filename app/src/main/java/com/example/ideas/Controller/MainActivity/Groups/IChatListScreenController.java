package com.example.ideas.Controller.MainActivity.Groups;

import android.content.Context;

public interface IChatListScreenController {

    void getGroups(String id);
    void onGroupClick(Context context, String chat_id, String group_name);

}
