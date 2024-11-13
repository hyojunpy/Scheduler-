package com.example.scheduler.repository;

import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;

import java.time.LocalDate;
import java.util.List;

public interface SchedulerRepository {
    // 일정 생성
    SchedulerResponseDto saveSchedule(Scheduler scheduler);
    // 전체 일정 조회
    List<SchedulerResponseDto> findAllSchedules(LocalDate updateDate, Long userId);
    // 선택 일정 조회
    Scheduler  findScheduleByIdOrElseThrow(Long userId);
    // 선택 일정 수정
    int updateSchedule(Long userId, String todo, String password);
    // 일정 삭제
    int deleteSchedule(Long userId, String password);
}