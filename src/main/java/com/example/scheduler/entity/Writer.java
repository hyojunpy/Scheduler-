package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Writer {
    private Long userId;
    private String name;
    private String email;
    private Timestamp createDate;
    private Timestamp updateDate;

    public Writer(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
