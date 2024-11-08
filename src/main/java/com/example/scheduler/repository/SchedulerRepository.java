package com.example.scheduler.repository;

import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;

import java.util.List;

public interface SchedulerRepository {
    SchedulerResponseDto saveSchedule(Scheduler scheduler);
    List<SchedulerResponseDto> findAllSchedules(String update_date, String writer);
    Scheduler  findScheduleByIdOrElseThrow(Long id);
}
