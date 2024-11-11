package com.example.scheduler.repository;

import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SchedulerRepositoryImpl implements SchedulerRepository {
    //
    private final JdbcTemplate jdbcTemplate;

    public SchedulerRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //일정 생성
    @Override
    public SchedulerResponseDto saveSchedule(Scheduler scheduler) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedules").usingGeneratedKeyColumns("id").usingColumns("todo","password", "userId");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", scheduler.getTodo());
        parameters.put("password", scheduler.getPassword());
        parameters.put("userId", scheduler.getUserId());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        long id = key.longValue();

        List<SchedulerResponseDto> schedulerResponseDtos = jdbcTemplate.query("SELECT id, todo, userId, createDate, updateDate FROM schedules WHERE id = ? ",scheduleRowMapper() ,id);

        if(!schedulerResponseDtos.isEmpty()) {
            return  schedulerResponseDtos.get(0);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "schedule not found");
        }
    }
    //전체 일정 조회
    @Override
    public List<SchedulerResponseDto> findAllSchedules(LocalDate updateDate, Long userId) {
        if(updateDate != null && userId != null ) {
            return jdbcTemplate.query("select * from schedules where DATE(updateDate) = ? AND userId = ? ORDER BY updateDate DESC ", scheduleRowMapper(), updateDate,userId);
        }
        else if(updateDate != null) {
            return jdbcTemplate.query("select * from schedules where DATE(updateDate) = ? ORDER BY updateDate DESC ", scheduleRowMapper(), updateDate );
        }
        else if (userId != null) {
            return jdbcTemplate.query("select * from schedules where userId = ? ORDER BY  updateDate DESC ", scheduleRowMapper(), userId);
        }
        else {
            return jdbcTemplate.query("select * from schedules", scheduleRowMapper());
        }
    }

    //선택 일정 조회
    @Override
    public Scheduler findScheduleByIdOrElseThrow(Long UserId) {
        List<Scheduler> result = jdbcTemplate.query("select * from schedules where UserId = ? ", schedulerRowMapperV2(), UserId);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists UserId = " + UserId));
    }

    //선택 일정 수정
    @Override
    public int updateSchedule(Long userId, String password, String todo) {
        if(todo == null) {
            return jdbcTemplate.update("UPDATE schedules SET userId = ? ,updateDate = now() WHERE userId = ? AND password = ? ",  userId, userId, password);
        }
        else if(userId == null) {
            return jdbcTemplate.update("UPDATE schedules SET todo = ?, updateDate = now() WHERE userId = ? AND password = ? ", todo, userId, password);
        }
        else{
            return jdbcTemplate.update("UPDATE schedules SET todo = ?, updateDate = now() WHERE userId = ? AND password = ? ", todo, userId, password);
        }
    }

    //선택 일정 삭제
    @Override
    public int deleteSchedule(Long userId, String password) {
        return jdbcTemplate.update("delete from schedules where userId = ? AND password = ?", userId, password);
    }



    //Row를 ResertSet에 매핑
    private RowMapper<SchedulerResponseDto> scheduleRowMapper() {
        return new RowMapper<SchedulerResponseDto>() {
            @Override
            public SchedulerResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SchedulerResponseDto(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getString("todo"),
                        rs.getTimestamp("createDate"),
                        rs.getTimestamp("updateDate")
                );
            }
        };
    }

    //Row를 ResertSet에 매핑
    private RowMapper<Scheduler> schedulerRowMapperV2() {
        return new RowMapper<Scheduler>() {
            @Override
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getString("todo"),
                        rs.getString("password"),
                        rs.getTimestamp("createDate"),
                        rs.getTimestamp("updateDate")
                );
            }
        };
    }
}