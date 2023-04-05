package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplateRepository implements RuleRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final RowMapper<Rule> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Rule.class);
    private static final String FIND_ALL = "SELECT * FROM rule";
    private static final String FIND_ACCIDENT_RULES = "SELECT * FROM rule WHERE id IN (:rIds)";

    @Override
    public List<Rule> findAll() {
        return jdbcTemplate.query(FIND_ALL, ROW_MAPPER);
    }

    @Override
    public Set<Rule> findAccidentRules(List<Integer> rIds) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("rIds", rIds);
        return new HashSet<>(namedParameterJdbcTemplate.query(FIND_ACCIDENT_RULES, parameters, ROW_MAPPER));
    }
}
