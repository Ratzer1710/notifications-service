package com.ratzer.Notification_Service.service.sender.channel;

import com.ratzer.Notification_Service.repository.entity.Channel;
import com.ratzer.Notification_Service.repository.entity.Notification;
import com.ratzer.Notification_Service.service.sender.ContactInfo;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailSenderChannel implements SenderChannel {

    private JavaMailSender mailSender;
    private final String from;

    public MailSenderChannel(JavaMailSender mailSender, String from) {
        this.mailSender = mailSender;
        this.from = from;
    }

    @Override
    public Channel getName() {
        return Channel.MAIL;
    }

    @Override
    public void send(ContactInfo contactInfo, Notification notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.from);
        message.setTo(contactInfo.getEmail());
        message.setSubject(notification.getTitle());
        message.setText(notification.getBody());
        this.mailSender.send(message);
    }
}
