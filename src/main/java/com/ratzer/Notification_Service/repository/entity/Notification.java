package com.ratzer.Notification_Service.repository.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Channel> channels;
    @Column(columnDefinition = "TIMESTAMP")
    private ZonedDateTime scheduledTime;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Notification() {
        // No args constructor
    }

    public Notification(String title, String body, List<Channel> channels, ZonedDateTime scheduledTime, Status status) {
        this.title = title;
        this.body = body;
        this.channels = channels;
        this.scheduledTime = scheduledTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public ZonedDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(ZonedDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(body, that.body) && Objects.equals(channels, that.channels) && Objects.equals(scheduledTime, that.scheduledTime) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, body, channels, scheduledTime, status);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", channels=" + channels +
                ", scheduledTime=" + scheduledTime +
                ", status=" + status +
                '}';
    }
}
