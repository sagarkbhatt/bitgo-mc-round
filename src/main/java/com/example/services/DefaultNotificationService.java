package com.example.services;

import com.example.dtos.NotificationDTO;
import com.example.models.Notification;
import com.example.models.NotificationStatus;
import com.example.repositories.NotificationAuditRepo;
import com.example.repositories.NotificationRepo;

import java.util.List;
import java.util.UUID;

public class DefaultNotificationService implements NotificationService {
    private NotificationRepo notificationRepo;
    private NotificationAuditRepo notificationAuditRepo;
    private EmailService emailService;

    public DefaultNotificationService(NotificationRepo notificationRepo,
                                      NotificationAuditRepo notificationAuditRepo,
                                      EmailService emailService) {
        this.emailService = emailService;
        this.notificationAuditRepo = notificationAuditRepo;
        this.notificationRepo = notificationRepo;
    }

    @Override
    public Notification createNotification(NotificationDTO notificationDTO) {
        System.out.println("Creating notification");
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
        notification.setStatus(NotificationStatus.SENT);
    }

    @Override
    public List<Notification> listNotification(NotificationStatus status) {
        System.out.println("Listing notification");
        return notificationAuditRepo.getNotificationByStatus(status);
    }

    @Override
    public Notification delete(UUID id) {
        System.out.println("Deleting notification");
        Notification deletedNotification = notificationRepo.delete(id);
        notificationAuditRepo.audit(NotificationStatus.DELETED, deletedNotification);
        deletedNotification.setStatus(NotificationStatus.DELETED);
        System.out.println("Notification deleted with id " + id);
        return deletedNotification;
    }
}
