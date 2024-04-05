package com.example.models;

import com.example.dtos.NotificationDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

// Stream of notification
// Send notification to all users associated to coin
// delete notification by id
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Notification {
    @EqualsAndHashCode.Include
    private UUID id;

    private Long currentPrice;

    private Long marketVolume;

    private LocalDate day;

    private Long dailyHigh;

    private Long marketCap;

    private NotificationStatus status;

    public Notification(Long currentPrice,
                        Long marketVolume,
                        LocalDate day,
                        Long dailyHigh, Long marketCap
    ) {
        this.id = UUID.randomUUID();
        this.currentPrice = currentPrice;
        this.marketVolume = marketVolume;
        this.day = day;
        this.dailyHigh = dailyHigh;
        this.marketCap = marketCap;
    }

    public static Notification from(NotificationDTO notificationDTO) {
        return new Notification(
                notificationDTO.getCurrentPrice(),
                notificationDTO.getMarketVolume(),
                notificationDTO.getDay(),
                notificationDTO.getDailyHigh(),
                notificationDTO.getMarketCap()
        );
    }
}
