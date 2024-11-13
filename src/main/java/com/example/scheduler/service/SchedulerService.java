package com.example.scheduler.service;

import com.example.scheduler.dto.SchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface SchedulerService {
    //일정 생성
    SchedulerResponseDto saveSchedule(String password, String todo, Long userid);
    //전체 일정 조회
    List<SchedulerResponseDto> findAllSchedules(LocalDate updateDate, Long userId);
    //선택 일정 조회
    SchedulerResponseDto findScheduleById(Long userId);
    //선택 일정 수정
    SchedulerResponseDto updateTodoOrWriter(Long Id, String name, String todo, String password );
    //일정 삭제
    void deleteSchedule(Long userId, String password);
}