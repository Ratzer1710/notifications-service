package com.ratzer.Notification_Service.service.sender.channel;

import com.ratzer.Notification_Service.repository.entity.Channel;
import com.ratzer.Notification_Service.repository.entity.Notification;
import com.ratzer.Notification_Service.service.sender.ContactInfo;

public interface SenderChannel {
    Channel getName();
    void send(ContactInfo contactInfo, Notification notification);
}
