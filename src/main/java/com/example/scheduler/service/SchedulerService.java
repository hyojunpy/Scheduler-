package com.example.scheduler.service;

import com.example.scheduler.dto.SchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SchedulerService {
    //일정 생성
    SchedulerResponseDto saveSchedule(SchedulerRequestDto dto);
    //전체 일정 조회
    List<SchedulerResponseDto> findAllSchedules(String update_date, String writer);
    //선택 일정 조회
    SchedulerResponseDto findScheduleById(Long id);
    //선택 일정 수정
    SchedulerResponseDto updateTodoOrWriter(Long id, String password, String todo,String writer);
    //일정 삭제
    void deleteSchedule(Long id, String password);
}