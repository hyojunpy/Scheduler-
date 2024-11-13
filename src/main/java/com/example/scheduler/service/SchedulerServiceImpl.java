package com.example.scheduler.service;

import com.example.scheduler.dto.SchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.repository.SchedulerRepository;
import com.example.scheduler.repository.WriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private final SchedulerRepository schedulerRepository;
    private final  WriterRepository writerRepository;

    public SchedulerServiceImpl(SchedulerRepository schedulerRepository, WriterRepository writerRepository) {
        this.schedulerRepository = schedulerRepository;
        this.writerRepository = writerRepository;
    }

    //일정 생성
    @Override
    public SchedulerResponseDto saveSchedule(String password , String todo, Long userid) {

        //일정, 작성자명, 패스워드 인자로 전달
        Scheduler scheduler = new Scheduler(userid, todo, password);

        return schedulerRepository.saveSchedule(scheduler);
    }

    //전체 일정 조회
    @Override
    public List<SchedulerResponseDto> findAllSchedules(LocalDate updateDate, Long userId) {

        return schedulerRepository.findAllSchedules(updateDate, userId);
    }

    //선택 일정 조회
    @Override
    public SchedulerResponseDto findScheduleById(Long userId) {

        Scheduler scheduler = schedulerRepository.findScheduleByIdOrElseThrow(userId);

        return new SchedulerResponseDto(scheduler);
    }

    //선택 일정 수정
    @Transactional
    @Override
    public SchedulerResponseDto updateTodoOrWriter(Long id, String name, String todo, String password ) {
        Long userId = schedulerRepository.findScheduleByIdOrElseThrow(id).getUserId();

        if (todo == null && name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The todo or writer are required values.");
        }
        int updatedRowName = writerRepository.updateUserName(userId, name);
        int updateRow = schedulerRepository.updateSchedule(id, todo, password);

        if (updatedRowName == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }

        Scheduler scheduler= schedulerRepository.findScheduleByIdOrElseThrow(id);

        return new SchedulerResponseDto(scheduler);
    }

    //일정 삭제
    @Override
    public void deleteSchedule(Long id, String password) {

        int deleteRow = schedulerRepository.deleteSchedule(id, password);

        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exitst id = " + id);
        }
    }

}