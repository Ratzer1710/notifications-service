package com.ratzer.Notification_Service.config;

import com.ratzer.Notification_Service.repository.NotificationRepository;
import com.ratzer.Notification_Service.service.factory.NotificationServiceFactory;
import com.ratzer.Notification_Service.service.factory.NotificationServiceFactoryImpl;
import com.ratzer.Notification_Service.service.sender.ContactInfo;
import com.ratzer.Notification_Service.service.sender.channel.*;
import com.ratzer.Notification_Service.service.sender.factory.NotificationSenderServiceFactory;
import com.ratzer.Notification_Service.service.sender.factory.NotificationSenderServiceFactoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${contact.email}")
    private String email;

    @Bean
    NotificationServiceFactory notificationServiceFactory(NotificationRepository notificationRepository) {
        return new NotificationServiceFactoryImpl(notificationRepository);
    }

    @Bean
    NotificationSenderServiceFactory notificationSenderServiceFactory(NotificationRepository notificationRepository, JavaMailSender javaMailSender) {
        List<SenderChannel> senderChannels = new ArrayList<>();
        senderChannels.add(new MailSenderChannel(javaMailSender, fromEmail));
        senderChannels.add(new PCPushSenderChannel());
        senderChannels.add(new MobileSenderChannel());
        return new NotificationSenderServiceFactoryImpl(senderChannels, new ContactInfo(email));
    }
}
