package com.example.services;

import com.example.dtos.NotificationDTO;
import com.example.models.Notification;
import com.example.models.NotificationStatus;
import com.example.repositories.NotificationAuditRepo;
import com.example.repositories.NotificationRepo;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

public class DefaultNotificationService implements NotificationService {
    private NotificationRepo notificationRepo;
    private NotificationAuditRepo notificationAuditRepo;
    private EmailService emailService;

    public DefaultNotificationService(NotificationRepo notificationRepo, NotificationAuditRepo notificationAuditRepo) {
        this.notificationAuditRepo = new NotificationAuditRepo();
        this.notificationRepo = new NotificationRepo();
    }

    @Override
    public Notification createNotification(NotificationDTO notificationDTO) {
        Notification notification = Notification.from(notificationDTO);
        notification.setStatus(NotificationStatus.NEW);
        notificationRepo.persist(notification);
        notificationAuditRepo.audit(notification.getStatus(), notification);
        sendEmail(notification);
        return notification;
    }

    private void sendEmail(Notification notification) {
        emailService.sendEmail(notification);
        notificationAuditRepo.audit(NotificationStatus.SENT, notification);
    }

    @Override
    public Notification listNotification(NotificationStatus status) {
        return null;
    }

    @Override
    public Notification delete(UUID id) {
        return null;
    }
}
