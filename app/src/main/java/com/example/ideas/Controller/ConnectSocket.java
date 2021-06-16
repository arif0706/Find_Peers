package com.example.ideas.Controller;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class ConnectSocket {
    Socket socket;
    {
        try {
            socket= IO.socket("http://10.0.2.2:8080");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
    public Socket getSocket(){
        return socket;
    }
}
