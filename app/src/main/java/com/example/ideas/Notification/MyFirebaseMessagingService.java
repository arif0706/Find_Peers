package com.example.ideas.Notification;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

public class MyFirebaseMessagingService extends  FirebaseMessagingService{
    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {


        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null){

            System.out.println("Notification title" + remoteMessage.getNotification().getBody());
            new NotificationHelper().displayNotification(this,remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());;
        }
        System.out.println(remoteMessage.getData());


    }

    @Override
    public void onNewToken(@NonNull @NotNull String s) {
        super.onNewToken(s);
        System.out.println("new token"+s);

    }
}
