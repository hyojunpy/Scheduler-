package com.example.scheduler.dto;

import lombok.Getter;

@Getter
public class SchedulerRequestDto {
    private Long userId;
    private String todo;
    private String password;
}