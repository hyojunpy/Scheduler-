package com.example.scheduler.dto;

import com.example.scheduler.entity.Scheduler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SchedulerResponseDto {
    private Long id;
    private String todo;
    private String writer;
    private String password;
    private LocalDate create_date;
    private LocalDate update_date;

    public SchedulerResponseDto(Scheduler scheduler) {

    }
}
