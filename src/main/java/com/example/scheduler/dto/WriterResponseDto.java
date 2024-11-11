package com.example.scheduler.dto;


import com.example.scheduler.entity.Writer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class WriterResponseDto {
    private Long userId;
    private String name;
    private String email;
    private Timestamp createDate;
    private Timestamp updateDate;


}
