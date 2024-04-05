package com.example.services;

import com.example.models.Notification;

import java.util.List;


public class DefaultEmailService implements EmailService{
    @Override
    public void sendEmail(List<Notification> notificationList) {
        for (Notification notification : notificationList) {
            sendEmail(notification);
        }
    }

    @Override
    public void sendEmail(Notification notification) {
        System.out.println("Email sent for notification: " + notification);

    }
}
