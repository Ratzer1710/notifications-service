package com.ratzer.Notification_Service.service;

import com.ratzer.Notification_Service.repository.entity.Notification;

public interface NotificationService {
    Notification createNotification(Notification notification);
}
