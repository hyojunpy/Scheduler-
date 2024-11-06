package com.example.scheduler.dto;

import com.example.scheduler.entity.Scheduler;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;

@Getter
@AllArgsConstructor
public class SchedulerResponseDto {

    private Long id;
    private String todo;
    private String writer;
    private String password;
    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public SchedulerResponseDto(Scheduler scheduler) {
        this.id = scheduler.getId();
        this.todo = scheduler.getTodo();
        this.writer = scheduler.getWriter();
        this.password = scheduler.getPassword();
    }

}
