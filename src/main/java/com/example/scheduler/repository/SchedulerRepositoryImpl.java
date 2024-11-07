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

    @Override
    public SchedulerResponseDto saveSchedule(Scheduler scheduler) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedules").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", scheduler.getTodo());
        parameters.put("writer", scheduler.getWriter());
        parameters.put("password", scheduler.getPassword());
        parameters.put("create_date", scheduler.getCreate_date());
        parameters.put("update_date", scheduler.getUpdate_date());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new SchedulerResponseDto(key.longValue(), scheduler.getTodo(), scheduler.getWriter(), scheduler.getPassword(), scheduler.getCreate_date().toLocalDate(), scheduler.getUpdate_date().toLocalDate());
    }

    @Override
    public List<SchedulerResponseDto> findAllSchedules(String update_date, String writer) {
        if(!update_date.isEmpty() && !writer.isEmpty()) {
            return jdbcTemplate.query("select * from schedules where DATE(update_date) = ? AND writer = ? ORDER BY update-date DESC ", scheduleRowMapper(), update_date,writer);
        }
        else if(!update_date.isEmpty()) {
            return jdbcTemplate.query("select * from schedules where DATE(update_date) = ? ORDER BY update_date DESC ", scheduleRowMapper(), update_date );
        }
        else if (!writer.isEmpty()) {
            return jdbcTemplate.query("select * from schedules where writer = ? ORDER BY  update_date DESC ", scheduleRowMapper(), writer);
        }
        else {
            return jdbcTemplate.query("select * from schedules", scheduleRowMapper());
        }
    }

    @Override
    public Scheduler findScheduleByIdOrElseThrow(Long id) {
        List<Scheduler> result = jdbcTemplate.query("select * from schedules where id = ? ", schedulerRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id));
    }

    @Override
    public int updateSchedule(Long id, String password, String todo, String writer) {
        if(todo == null) {
            return jdbcTemplate.update("UPDATE schedules SET writer = ? ,update_date = now() WHERE id = ? AND password = ? ",  writer, id, password);
        }
        else if(writer == null) {
            return jdbcTemplate.update("UPDATE schedules SET todo = ?, update_date = now() WHERE id = ? AND password = ? ", todo, id, password);
        }
        else{
            return jdbcTemplate.update("UPDATE schedules SET todo = ?, writer = ? ,update_date = now() WHERE id = ? AND password = ? ", todo, writer, id, password);
        }
    }

    @Override
    public int deleteSchedule(Long id, String password) {
        return jdbcTemplate.update("delete from schedules where id = ? AND password = ?", id, password);
    }




    private RowMapper<SchedulerResponseDto> scheduleRowMapper() {
        return new RowMapper<SchedulerResponseDto>() {
            @Override
            public SchedulerResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SchedulerResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("writer"),
                        rs.getString("password"),
                        rs.getTimestamp("create_date").toLocalDateTime().toLocalDate(),
                        rs.getTimestamp("update_date").toLocalDateTime().toLocalDate()
                );
            }
        };
    }

    private RowMapper<Scheduler> schedulerRowMapperV2() {
        return new RowMapper<Scheduler>() {
            @Override
            public Scheduler mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Scheduler(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("writer"),
                        rs.getString("password"),
                        rs.getTimestamp("create_date").toLocalDateTime(),
                        rs.getTimestamp("update_date").toLocalDateTime()
                );
            }
        };
    }
}
