package com.example.services;

import com.example.models.Notification;

import java.util.List;

public interface EmailService {

    void sendEmail(List<Notification> notificationList);

    void sendEmail(Notification notification);
}
