package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Scheduler implements Serializable {
    private Long id;
    private Long userId;
    private String todo;
    private String password;
    private Timestamp createDate;
    private Timestamp updateDate;

    public Scheduler(Long userId,  String todo, String password) {
        this.todo = todo;
        this.password = password;
        this.userId = userId;
    }
}