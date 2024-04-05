package com.example.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class NotificationDTO {
    private Long currentPrice;

    private Long marketVolume;

    private LocalDate day;

    private Long dailyHigh;

    private Long marketCap;
}
