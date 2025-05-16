package com.ratzer.Notification_Service.service.factory;

import com.ratzer.Notification_Service.repository.NotificationRepository;
import com.ratzer.Notification_Service.service.NotificationService;
import com.ratzer.Notification_Service.service.NotificationServiceImpl;

public class NotificationServiceFactoryImpl implements NotificationServiceFactory {
    private NotificationRepository notificationRepository;

    public NotificationServiceFactoryImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public NotificationService getInstance() {
        return new NotificationServiceImpl(this.notificationRepository);
    }
}
