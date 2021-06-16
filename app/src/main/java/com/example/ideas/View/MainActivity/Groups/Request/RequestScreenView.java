package com.example.ideas.View.MainActivity.Groups.Request;

import com.example.ideas.Model.Requests.Requests_list;

import java.util.List;

public interface RequestScreenView {
    void onFetchedRequests(List<Requests_list> requests_lists);
}
