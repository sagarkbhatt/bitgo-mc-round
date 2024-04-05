package com.example.services;

import com.example.dtos.NotificationDTO;
import com.example.models.Notification;
import com.example.models.NotificationStatus;

import java.util.UUID;

public interface NotificationService {

    Notification createNotification(NotificationDTO notificationDTO);

    void sendEmail(Notification notification);

    Notification listNotification(NotificationStatus status);

    Notification delete(UUID id);
}
