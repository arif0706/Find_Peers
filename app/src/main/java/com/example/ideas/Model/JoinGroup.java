package com.example.ideas.Model;

public class JoinGroup {

    public String mongo_user_id
            ;
    public String group_id;
    public String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getMongo_user_id() {
        return mongo_user_id;
    }

    public void setMongo_user_id(String mongo_user_id) {
        this.mongo_user_id = mongo_user_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
