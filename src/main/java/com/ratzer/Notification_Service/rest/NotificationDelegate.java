package com.ratzer.Notification_Service.rest;

import com.ratzer.Notification_Service.api.NotificationsApiDelegate;
import com.ratzer.Notification_Service.api.model.Notification;
import com.ratzer.Notification_Service.rest.mapper.NotificationMapper;
import com.ratzer.Notification_Service.service.NotificationService;
import com.ratzer.Notification_Service.service.factory.NotificationServiceFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationDelegate implements NotificationsApiDelegate {

    private NotificationService service;
    private NotificationMapper mapper;

    public NotificationDelegate(NotificationServiceFactory notificationServiceFactory) {
        this.service = notificationServiceFactory.getInstance();
        this.mapper = new NotificationMapper();
    }

    @Override
    public ResponseEntity<Notification> createNotification(Notification notification) {
        com.ratzer.Notification_Service.repository.entity.Notification notificationEntity = mapper.mapNotificationEntity(notification);
        notificationEntity = service.createNotification(notificationEntity);
        Notification response = mapper.mapNotificationApi(notificationEntity);
        return ResponseEntity.status(201).body(response);
    }
}
