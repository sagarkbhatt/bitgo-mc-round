package com.example.repositories;

import com.example.models.Notification;
import com.example.models.NotificationStatus;

import java.util.*;

public class NotificationAuditRepo {

    private Map<NotificationStatus, Set<Notification>> notificationAudit;

    public NotificationAuditRepo() {
        this.notificationAudit = new HashMap<>();
    }

    public void audit(NotificationStatus status, Notification notification) {

        Set<Notification> currentStatusNotifications = notificationAudit
                .getOrDefault(notification.getStatus(), new HashSet<>());
        currentStatusNotifications.remove(notification);
        notificationAudit.put(notification.getStatus(), currentStatusNotifications);

        Set<Notification> notifications = notificationAudit.getOrDefault(status, new HashSet<>());
        notifications.add(notification);
        notificationAudit.put(status, notifications);
    }

    public List<Notification> getNotificationByStatus(NotificationStatus status) {
        Set<Notification> notifications = notificationAudit.getOrDefault(status, new HashSet<>());
        return new ArrayList<>(notifications);
    }
}
