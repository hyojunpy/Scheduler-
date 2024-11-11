package com.example.scheduler.controller;

import com.example.scheduler.dto.SchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.service.SchedulerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/schedules")
public class SchedulerController {

    private final SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    //일정 생성
    @PostMapping("/{userId}")
    public ResponseEntity<SchedulerResponseDto> createSchedule(
            @RequestBody SchedulerRequestDto dto
            , @PathVariable Long userId ) {
        //Request로 값 전달 받아 Service.saveScehdule로 넘김
        return new ResponseEntity<>(schedulerService.saveSchedule(dto.getPassword(), dto.getTodo(), userId), HttpStatus.CREATED);
    }

    //전체 일정 조회
    @GetMapping
    public List<SchedulerResponseDto> findAllSchedules(
            @RequestParam(defaultValue = "", value = "updateDate", required = false) LocalDate updateDate,
            @RequestParam(defaultValue = "", value = "userId", required = false) Long userId) {
        List<SchedulerResponseDto> responseList = new ArrayList<>();

        return schedulerService.findAllSchedules(updateDate, userId);
    }
    //선택 일정 조회
    @GetMapping("/{userId}")
    public ResponseEntity<SchedulerResponseDto> findScheduleById(@PathVariable Long userId) {
        return new ResponseEntity<>(schedulerService.findScheduleById(userId), HttpStatus.OK);
    }
    //선택 일정 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<SchedulerResponseDto> updateTodoOrWriter(
            @PathVariable Long userId,
            @RequestBody SchedulerRequestDto dto
    ) {
        return new ResponseEntity<>(schedulerService.updateTodoOrWriter(userId, dto.getPassword(), dto.getTodo()), HttpStatus.OK);
    }
    //선택 일정 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long userId,
            @RequestBody SchedulerRequestDto dto
    ) {
        schedulerService.deleteSchedule(userId, dto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}