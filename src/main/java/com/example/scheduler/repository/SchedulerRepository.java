package com.example.scheduler.repository;

import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;

import java.util.List;

public interface SchedulerRepository {
    // 일정 생성
    SchedulerResponseDto saveSchedule(Scheduler scheduler);
    // 전체 일정 조회
    List<SchedulerResponseDto> findAllSchedules(String update_date, String writer);
    // 선택 일정 조회
    Scheduler  findScheduleByIdOrElseThrow(Long id);
    // 선택 일정 수정
    int updateSchedule(Long id, String password, String todo, String writer);
    // 일정 삭제
    int deleteSchedule(Long id, String password);
}