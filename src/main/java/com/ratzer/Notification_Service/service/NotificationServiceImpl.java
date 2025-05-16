package com.ratzer.Notification_Service.service;

import com.ratzer.Notification_Service.repository.NotificationRepository;
import com.ratzer.Notification_Service.repository.entity.Notification;

public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
