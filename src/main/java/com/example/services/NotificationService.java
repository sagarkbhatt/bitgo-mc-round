package com.example.services;

import com.example.dtos.NotificationDTO;
import com.example.models.Notification;
import com.example.models.NotificationStatus;

import java.util.List;
import java.util.UUID;

public interface NotificationService {

    Notification createNotification(NotificationDTO notificationDTO);

    List<Notification> listNotification(NotificationStatus status);

    Notification delete(UUID id);
}
