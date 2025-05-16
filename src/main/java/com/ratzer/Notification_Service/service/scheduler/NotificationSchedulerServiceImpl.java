package com.ratzer.Notification_Service.service.scheduler;

import com.ratzer.Notification_Service.repository.NotificationRepository;
import com.ratzer.Notification_Service.repository.entity.Notification;
import com.ratzer.Notification_Service.repository.entity.Status;
import com.ratzer.Notification_Service.service.sender.NotificationSenderService;
import com.ratzer.Notification_Service.service.sender.factory.NotificationSenderServiceFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class NotificationSchedulerServiceImpl implements NotificationSchedulerService {
    private NotificationRepository repository;
    private NotificationSenderService sender;

    public NotificationSchedulerServiceImpl(NotificationRepository repository, NotificationSenderServiceFactory senderFactory) {
        this.repository = repository;
        this.sender = senderFactory.getInstance();
    }

    @Scheduled(fixedRate = 60000)
    @Override
    public void processNotifications() {
        List<Notification> pendingNotifications = this.repository.findByStatusAndScheduledTimeBefore(Status.PENDING, ZonedDateTime.now());

        for (Notification notification : pendingNotifications) {
            try {
                sender.send(notification);
                notification.setStatus(Status.COMPLETED);
            } catch (Exception e) {
                notification.setStatus(Status.ERROR);
            }
            this.repository.save(notification);
        }
    }
}
