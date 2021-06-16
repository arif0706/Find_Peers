package com.example.ideas.Model.Chat;

public class Typing {

    public String roomName;
    public String userName;

    public Typing(String roomName, String userName) {
        this.roomName = roomName;
        this.userName = userName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
