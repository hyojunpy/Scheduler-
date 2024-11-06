package com.example.scheduler.repository;

import com.example.scheduler.dto.SchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SchedulerRepositoryImpl implements SchedulerRepository {

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

        return new SchedulerResponseDto(key.longValue(), scheduler.getTodo(), scheduler.getWriter(), scheduler.getPassword(), scheduler.getCreate_date(), scheduler.getUpdate_date());
    }

    @Override
    public List<SchedulerResponseDto> findAllSchedules() {

        return jdbcTemplate.query("select * from schedules", scheduleRowMapper());
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
                        rs.getTimestamp("create_date").toLocalDateTime(),
                        rs.getTimestamp("update_date").toLocalDateTime()
                );
            }
        };
    }

}
