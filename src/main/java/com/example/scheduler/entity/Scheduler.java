package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Scheduler implements Serializable {
    private Long id;
    private String todo;
    private String writer;
    private String password;
    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public Scheduler(String todo, String writer, String password) {
        this.todo = todo;
        this.writer = writer;
        this.password = password;
        this.create_date = LocalDateTime.now();
        this.update_date = LocalDateTime.now();
    }

 }