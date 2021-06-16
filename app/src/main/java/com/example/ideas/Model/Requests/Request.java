package com.example.ideas.Model.Requests;

import java.util.List;

public class Request {
    String user_id;
    List<Requests_list> requests_list;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<Requests_list> getRequests_list() {
        return requests_list;
    }

    public void setRequests_list(List<Requests_list> requests_list) {
        this.requests_list = requests_list;
    }
}
