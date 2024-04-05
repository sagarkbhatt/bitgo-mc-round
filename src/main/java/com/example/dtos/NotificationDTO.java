package com.example.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Data
public class NotificationDTO {
    private String currentPrice;

    private Long marketVolume;

    private LocalDate day;

    private Long dailyHigh;

    private Long marketCap;
}
