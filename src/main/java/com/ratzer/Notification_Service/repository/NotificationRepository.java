package com.ratzer.Notification_Service.repository;

import com.ratzer.Notification_Service.repository.entity.Notification;
import com.ratzer.Notification_Service.repository.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatusAndScheduledTimeBefore(Status status, ZonedDateTime time);
}
