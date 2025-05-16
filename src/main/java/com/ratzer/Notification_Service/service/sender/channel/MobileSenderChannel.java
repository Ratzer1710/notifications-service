package com.ratzer.Notification_Service.service.sender.channel;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.ratzer.Notification_Service.repository.entity.Channel;
import com.ratzer.Notification_Service.repository.entity.Notification;
import com.ratzer.Notification_Service.service.sender.ContactInfo;

public class MobileSenderChannel implements SenderChannel {

    @Override
    public Channel getName() {
        return Channel.MOBILE;
    }

    @Override
    public void send(ContactInfo contactInfo, Notification notification) {
        try {
            com.google.firebase.messaging.Notification content = com.google.firebase.messaging.Notification.builder()
                    .setTitle(notification.getTitle())
                    .setBody(notification.getBody())
                    .build();

            Message msg = Message.builder()
                    .setNotification(content)
                    .setTopic("general")
                    .build();

            FirebaseMessaging.getInstance().send(msg);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
