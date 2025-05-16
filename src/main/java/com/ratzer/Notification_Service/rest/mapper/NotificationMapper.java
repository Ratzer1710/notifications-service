package com.ratzer.Notification_Service.rest.mapper;

import com.ratzer.Notification_Service.repository.entity.Channel;
import com.ratzer.Notification_Service.repository.entity.Notification;
import com.ratzer.Notification_Service.repository.entity.Status;

import java.util.ArrayList;
import java.util.List;

public class NotificationMapper {

    public List<Channel> mapChannelsEntity(List<com.ratzer.Notification_Service.api.model.Channel> channelsApi) {
        List<Channel> channels = new ArrayList<>();

        for (com.ratzer.Notification_Service.api.model.Channel channel : channelsApi) {
            channels.add(Channel.valueOf(channel.name()));
        }

        return channels;
    }

    public List<com.ratzer.Notification_Service.api.model.Channel> mapChannelsApi(List<Channel> channels) {
        List<com.ratzer.Notification_Service.api.model.Channel> channelsApi = new ArrayList<>();

        for (Channel channel : channels) {
            channelsApi.add(com.ratzer.Notification_Service.api.model.Channel.valueOf(channel.name()));
        }

        return channelsApi;
    }

    public Notification mapNotificationEntity(com.ratzer.Notification_Service.api.model.Notification notificationApi) {
        return new Notification(
                notificationApi.getTitle(),
                notificationApi.getBody(),
                mapChannelsEntity(notificationApi.getChannels()),
                notificationApi.getDatetime(),
                Status.PENDING
        );
    }

    public com.ratzer.Notification_Service.api.model.Notification mapNotificationApi(Notification notification) {
        return new com.ratzer.Notification_Service.api.model.Notification()
                .title(notification.getTitle())
                .body(notification.getBody())
                .channels(mapChannelsApi(notification.getChannels()))
                .datetime(notification.getScheduledTime())
                .status(com.ratzer.Notification_Service.api.model.Status.valueOf(notification.getStatus().name()));
    }
}
