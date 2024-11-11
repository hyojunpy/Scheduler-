package com.example.scheduler.service;

import com.example.scheduler.dto.WriterRequestDto;
import com.example.scheduler.dto.WriterResponseDto;
import com.example.scheduler.entity.Writer;
import com.example.scheduler.repository.WriterRepository;
import org.springframework.stereotype.Service;

@Service
public class WriterServiceImpl implements WriterService{
    private final WriterRepository writerRepository;

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }


    @Override
    public WriterResponseDto saveWriter(WriterRequestDto dto) {

        Writer writer = new Writer(dto.getName(),dto.getEmail());

        return writerRepository.saveWriter(writer);
    }


}
