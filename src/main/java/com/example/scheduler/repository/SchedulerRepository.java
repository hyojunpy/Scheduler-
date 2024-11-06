package com.example.scheduler.repository;

import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface SchedulerRepository {
    SchedulerResponseDto saveSchedule(Scheduler scheduler);
    List<SchedulerResponseDto> findAllSchedules(String update_date, String writer);
    Scheduler  findScheduleByIdOrElseThrow(Long id);
}
