package com.example.scheduler.controller;

import com.example.scheduler.dto.WriterRequestDto;
import com.example.scheduler.dto.WriterResponseDto;
import com.example.scheduler.service.WriterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/writer")
public class WriterController {

    private final WriterService writerService;


    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    @PostMapping
    public ResponseEntity<WriterResponseDto> createWriter(@RequestBody WriterRequestDto dto) {
        return new ResponseEntity<>(writerService.saveWriter(dto), HttpStatus.CREATED);
    }

}
