package com.example.repositories;

import com.example.models.Notification;

import java.util.*;

public class NotificationRepo {

    private Map<UUID, Notification> notificationMap;

    public NotificationRepo() {
        this.notificationMap = new HashMap<>();
    }

    public Notification persist(Notification notification) {
        notificationMap.put(notification.getId(), notification);
        return notification;
    }

    public Notification get(UUID id) {
        return notificationMap.get(id);
    }

    public Notification delete(UUID id) {
        return notificationMap.remove(id);
    }
}
