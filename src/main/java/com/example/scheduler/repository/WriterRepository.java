package com.example.scheduler.repository;

import com.example.scheduler.dto.WriterResponseDto;
import com.example.scheduler.entity.Writer;

public interface WriterRepository {
    WriterResponseDto saveWriter(Writer writer);
}
