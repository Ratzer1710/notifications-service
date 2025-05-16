package com.ratzer.Notification_Service.service.sender.channel;

import com.ratzer.Notification_Service.repository.entity.Channel;
import com.ratzer.Notification_Service.repository.entity.Notification;
import com.ratzer.Notification_Service.service.sender.ContactInfo;

import java.awt.*;

public class PCPushSenderChannel implements SenderChannel {

    @Override
    public Channel getName() {
        return Channel.PC;
    }

    @Override
    public void send(ContactInfo contactInfo, Notification notification) {
        try {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            TrayIcon trayIcon = new TrayIcon(image, "Notification");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("New Notification");
            tray.add(trayIcon);

            trayIcon.displayMessage(notification.getTitle(), notification.getBody(), TrayIcon.MessageType.INFO);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
