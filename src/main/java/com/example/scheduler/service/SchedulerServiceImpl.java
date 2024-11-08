package com.example.scheduler.service;

import com.example.scheduler.dto.SchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.repository.SchedulerRepository;
import org.springframework.stereotype.Service;

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
    public List<SchedulerResponseDto> findAllSchedules(String update_date, String writer) {

        return schedulerRepository.findAllSchedules(update_date, writer);
    }

    @Override
    public SchedulerResponseDto findScheduleById(Long id) {

        Scheduler scheduler = schedulerRepository.findScheduleByIdOrElseThrow(id);

        return new SchedulerResponseDto(scheduler);
    }
}
