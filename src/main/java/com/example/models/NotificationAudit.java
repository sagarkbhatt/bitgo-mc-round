package com.example.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NotificationAudit {
    @EqualsAndHashCode.Include
    private UUID id;
    private Notification notification;
    private User user;
    private NotificationStatus notificationStatus;
}
