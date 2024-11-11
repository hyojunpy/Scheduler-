package com.example.scheduler.service;

import com.example.scheduler.dto.WriterRequestDto;
import com.example.scheduler.dto.WriterResponseDto;

public interface WriterService {
    WriterResponseDto saveWriter(WriterRequestDto dto);
}
