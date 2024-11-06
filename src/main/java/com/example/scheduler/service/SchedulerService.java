package com.example.scheduler.service;

import com.example.scheduler.dto.SchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;

import java.util.List;

public interface SchedulerService {
    SchedulerResponseDto saveSchedule(SchedulerRequestDto dto);
    List<SchedulerResponseDto> findAllSchedules(String update_date, String writer);
    SchedulerResponseDto findScheduleById(Long id);
}
