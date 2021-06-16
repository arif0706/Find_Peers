package com.example.ideas.Model;

import com.example.ideas.Model.Chat.Message;

public class Group {
    public String group_name;
    public String[] group_members;
    public String group_admin;
    public String _id;
    public String chat_id;
    public Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String[] getGroup_members() {
        return group_members;
    }

    public void setGroup_members(String[] group_members) {
        this.group_members = group_members;
    }

    public String getGroup_admin() {
        return group_admin;
    }

    public void setGroup_admin(String group_admin) {
        this.group_admin = group_admin;
    }
}
