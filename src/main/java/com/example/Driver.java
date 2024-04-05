package com.example;

import com.example.dtos.NotificationDTO;
import com.example.models.Notification;
import com.example.models.NotificationStatus;
import com.example.repositories.NotificationAuditRepo;
import com.example.repositories.NotificationRepo;
import com.example.services.DefaultEmailService;
import com.example.services.DefaultNotificationService;
import com.example.services.EmailService;
import com.example.services.NotificationService;

import java.time.LocalDate;
import java.util.List;

public class Driver {

    public static void main(String[] args) {
        NotificationRepo notificationRepo = new NotificationRepo();
        NotificationAuditRepo notificationAuditRepo = new NotificationAuditRepo();
        EmailService emailService = new DefaultEmailService();
        NotificationService notificationService = new DefaultNotificationService(
                notificationRepo, notificationAuditRepo,
                emailService);
        LocalDate now = LocalDate.now();

        Notification notificationOne = notificationService.createNotification(
                buildNotification(100L, 100L,
                        10000L, 2000L, now));

        Notification notificationTwo = notificationService.createNotification(
                buildNotification(200L, 100L,
                10000L, 2000L, now));

        Notification notificationThree = notificationService.createNotification(
                buildNotification(300L, 100L,
                10000L, 2000L, now));

        System.out.println("Fetching all sent notifications");
        List<Notification> notifications = notificationService.listNotification(NotificationStatus.SENT);
        System.out.println("Here is the list");
        System.out.println(notifications);

        System.out.println("Deleting notification with id " + notificationOne.getId());
        notificationService.delete(notificationOne.getId());

        System.out.println("Fetching all the deleted notifications from audit");
        List<Notification> deletedNotifications = notificationService.listNotification(NotificationStatus.DELETED);
        System.out.println("Here is the list");
        System.out.println(deletedNotifications);
    }

    private static NotificationDTO buildNotification(long currentPrice,
                                                     long dailyHigh,
                                                     long marketCap,
                                                     long marketVolume, LocalDate date) {
        return NotificationDTO
                .builder()
                .currentPrice(currentPrice)
                .dailyHigh(dailyHigh)
                .marketCap(marketCap)
                .marketVolume(marketVolume)
                .day(date)
                .build();
    }

}