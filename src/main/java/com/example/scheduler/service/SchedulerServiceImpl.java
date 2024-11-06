package com.example.scheduler.service;

import com.example.scheduler.dto.SchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.repository.SchedulerRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private final SchedulerRepository schedulerRepository;

    public SchedulerServiceImpl(SchedulerRepository schedulerRepository) {
        this.schedulerRepository = schedulerRepository;
    }

    @Override
    public SchedulerResponseDto saveSchedule(SchedulerRequestDto dto) {

        Scheduler scheduler = new Scheduler( dto.getTodo(), dto.getWriter(), dto.getPassword());

        return schedulerRepository.saveSchedule(scheduler);
    }

    @Override
    public List<SchedulerResponseDto> findAllSchedules() {

        return schedulerRepository.findAllSchedules();
    }
}
