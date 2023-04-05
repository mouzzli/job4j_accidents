package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplateRepository implements AccidentTypeRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<AccidentType> ROW_MAPPER = BeanPropertyRowMapper.newInstance(AccidentType.class);
    private static final String FIND_ALL = "SELECT * FROM accident_type";
    private static final String FIND_BY_ID = "SELECT * FROM accident_type WHERE id = ?";

    @Override
    public List<AccidentType> findAll() {
        return jdbcTemplate.query(FIND_ALL, ROW_MAPPER);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, ROW_MAPPER, id));
    }
}
