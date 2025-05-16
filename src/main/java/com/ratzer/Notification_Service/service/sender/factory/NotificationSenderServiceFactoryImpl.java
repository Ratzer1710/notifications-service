package com.ratzer.Notification_Service.service.sender.factory;

import com.ratzer.Notification_Service.service.sender.ContactInfo;
import com.ratzer.Notification_Service.service.sender.NotificationSenderService;
import com.ratzer.Notification_Service.service.sender.NotificationSenderServiceImpl;
import com.ratzer.Notification_Service.service.sender.channel.SenderChannel;

import java.util.List;

public class NotificationSenderServiceFactoryImpl implements NotificationSenderServiceFactory {
    private List<SenderChannel> channels;
    private ContactInfo contactInfo;

    public NotificationSenderServiceFactoryImpl(List<SenderChannel> channels, ContactInfo contactInfo) {
        this.channels = channels;
        this.contactInfo = contactInfo;
    }

    @Override
    public NotificationSenderService getInstance() {
        return new NotificationSenderServiceImpl(channels, contactInfo);
    }
}
