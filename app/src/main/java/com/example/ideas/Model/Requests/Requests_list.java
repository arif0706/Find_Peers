package com.example.ideas.Model.Requests;

import com.example.ideas.Model.Group;
import com.example.ideas.Model.User;

public class Requests_list {
    String _id;
    User requested_by;
    Group group_id;
    boolean accepted;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public User getRequested_by() {
        return requested_by;
    }

    public void setRequested_by(User requested_by) {
        this.requested_by = requested_by;
    }

    public Group getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Group group_id) {
        this.group_id = group_id;
    }
}
