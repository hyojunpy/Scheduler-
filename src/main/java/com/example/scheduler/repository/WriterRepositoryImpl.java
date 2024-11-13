package com.example.scheduler.repository;

import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.dto.WriterRequestDto;
import com.example.scheduler.dto.WriterResponseDto;
import com.example.scheduler.entity.Writer;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WriterRepositoryImpl implements WriterRepository{

    private final JdbcTemplate jdbcTemplate;

    public WriterRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public WriterResponseDto saveWriter(Writer writer) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("userid").usingColumns("name","email");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", writer.getName());
        parameters.put("email", writer.getEmail());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        long writerid = key.longValue();
        List<WriterResponseDto> writerRequestDtos = jdbcTemplate.query("SELECT * FROM user WHERE userid = ? " ,writerRowMapper() ,writerid);

        if(!writerRequestDtos.isEmpty()) {
           return writerRequestDtos.get(0);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "schedule not found");
        }
//        return new WriterResponseDto(key.longValue(), writer.getName(), writer.getEmail(), writer.getCreateDate(), writer.getUpdateDate());
    }

    @Override
    public int updateUserName(Long userId, String name) {
        return jdbcTemplate.update("UPDATE user SET name = ? WHERE userId = ? ",  name, userId);
    }

    private RowMapper<WriterResponseDto> writerRowMapper() {
        return new RowMapper<WriterResponseDto>() {
            @Override
            public WriterResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new WriterResponseDto(
                        rs.getLong("userid"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("createDate"),
                        rs.getTimestamp("updateDate")
                );
            }
        };
    }
}
