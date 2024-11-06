package com.example.scheduler.Controller;

import com.example.scheduler.dto.SchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.service.SchedulerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/schedules")
public class SchedulerController {

    private SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostMapping
    public ResponseEntity<SchedulerResponseDto> createSchedule(@RequestBody SchedulerRequestDto dto) {

        return new ResponseEntity<>(schedulerService.saveSchedule(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{}")
    public List<SchedulerResponseDto> findAllSchedules() {
        List<SchedulerResponseDto> responseList = new ArrayList<>();

        return schedulerService.findAllSchedules();
    }

}
