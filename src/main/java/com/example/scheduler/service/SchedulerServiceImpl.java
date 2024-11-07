package com.example.scheduler.service;

import com.example.scheduler.dto.SchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.repository.SchedulerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional
    @Override
    public SchedulerResponseDto updateTodoOrWriter(Long id, String password, String todo, String writer) {

        if (todo == null && writer == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The todo or writer are required values.");
        }

        int updateRow = schedulerRepository.updateSchedule(id, password, todo, writer);

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id or password = " + id);
        }

        Scheduler scheduler= schedulerRepository.findScheduleByIdOrElseThrow(id);

        return new SchedulerResponseDto(scheduler);
    }
    @Override
    public void deleteSchedule(Long id, String password) {

        int deleteRow = schedulerRepository.deleteSchedule(id, password);

        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exitst id = " + id);
        }
    }

}
