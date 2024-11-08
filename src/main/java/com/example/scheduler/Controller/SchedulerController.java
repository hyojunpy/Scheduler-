package com.example.scheduler.Controller;

import com.example.scheduler.dto.SchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.service.SchedulerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/schedules")
public class SchedulerController {

    private final SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    //일정 생성
    @PostMapping
    public ResponseEntity<SchedulerResponseDto> createSchedule(@RequestBody SchedulerRequestDto dto) {
        //Request로 값 전달 받아 Service.saveScehdule로 넘김
        return new ResponseEntity<>(schedulerService.saveSchedule(dto), HttpStatus.CREATED);
    }

    //전체 일정 조회
    @GetMapping
    public List<SchedulerResponseDto> findAllSchedules(
            @RequestParam(defaultValue = "", value = "update_date", required = false) String update_date,
            @RequestParam(defaultValue = "", value = "writer", required = false) String writer) {
        List<SchedulerResponseDto> responseList = new ArrayList<>();

        return schedulerService.findAllSchedules(update_date, writer);
    }
    //선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<SchedulerResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(schedulerService.findScheduleById(id), HttpStatus.OK);
    }
    //선택 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<SchedulerResponseDto> updateTodoOrWriter(
            @PathVariable Long id,
            @RequestBody SchedulerRequestDto dto
    ) {
        return new ResponseEntity<>(schedulerService.updateTodoOrWriter(id, dto.getPassword(), dto.getTodo(), dto.getWriter()), HttpStatus.OK);
    }
    //선택 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestBody SchedulerRequestDto dto
    ) {
        schedulerService.deleteSchedule(id, dto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}