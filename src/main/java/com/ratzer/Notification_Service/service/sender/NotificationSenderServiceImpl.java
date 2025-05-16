package com.ratzer.Notification_Service.service.sender;

import com.ratzer.Notification_Service.repository.entity.Notification;
import com.ratzer.Notification_Service.service.sender.channel.SenderChannel;

import java.util.List;

public class NotificationSenderServiceImpl implements NotificationSenderService {

    List<SenderChannel> channels;
    private final ContactInfo contactInfo;

    public NotificationSenderServiceImpl(List<SenderChannel> channels, ContactInfo contactInfo) {
        this.channels = channels;
        this.contactInfo = contactInfo;
    }

    @Override
    public void send(Notification notification) {
        for (SenderChannel senderChannel : channels) {
            if (notification.getChannels().contains(senderChannel.getName())) {
                senderChannel.send(contactInfo, notification);
            }
        }
    }
}
