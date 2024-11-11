package com.example.scheduler.dto;

import com.example.scheduler.entity.Scheduler;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SchedulerResponseDto {
    private Long id;
    private Long userId;
    private String todo;
    private Timestamp createDate;
    private Timestamp updateDate;
//
    public SchedulerResponseDto(Scheduler scheduler) {
        this.id = scheduler.getId();
        this.userId = scheduler.getUserId();
        this.todo = scheduler.getTodo();
        this.createDate = scheduler.getCreateDate();
        this.updateDate = scheduler.getUpdateDate();
    }
}
