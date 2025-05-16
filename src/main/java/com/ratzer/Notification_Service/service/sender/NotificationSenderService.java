package com.ratzer.Notification_Service.service.sender;

import com.ratzer.Notification_Service.repository.entity.Notification;

public interface NotificationSenderService {
    void send(Notification notification);
}
