package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@AllArgsConstructor
public class AccidentJdbcTemplateRepository implements AccidentRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<Accident> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Accident.class);
    private static final String SAVE = "INSERT INTO accident (number, text, address, type_id) "
            + "VALUES (?, ?, ?, ?)";
    private static final String FIND_ALL = "SELECT a.id, a.number, a.text, a.address, a.type_id, t.name as type_name, r.id as rule_id, r.name as rule_name "
            + "FROM accident a "
            + "JOIN accident_type t ON a.type_id = t.id "
            + "LEFT JOIN accident_rule ar ON a.id = ar.accident_id "
            + "LEFT JOIN rule r ON ar.rule_id = r.id "
            + "ORDER BY a.id";
    private static final String FIND_BY_ID = "SELECT * FROM accident "
            + "WHERE id = ?";
    private static final String UPDATE = "UPDATE accident "
            + "SET number = ?, text = ?, address = ?, type_id = ? "
            + "WHERE id = ?";
    private static final String SET_ACCIDENT_RULE = "INSERT INTO accident_rule (rule_id, accident_id)"
            + "VALUES (?, ?)";
    private static final String DELETE_ACCIDENT_RULE = "DELETE FROM accident_rule "
            + "WHERE accident_id = ?";

    @Override
    @Transactional
    public Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SAVE, new String[]{"id"});
            ps.setString(1, accident.getNumber());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        saveAccidentRule(new ArrayList<>(accident.getRules()), accident.getId());
        return accident;
    }

    @Override
    public List<Accident> findAll() {
        return jdbcTemplate.query(FIND_ALL, (rs) -> {
            List<Accident> accidentList = new ArrayList<>();
            while (rs.next()) {
                Accident accident = new Accident();
                accident.setId(rs.getInt("id"));
                accident.setNumber(rs.getString("number"));
                accident.setText(rs.getString("text"));
                accident.setAddress(rs.getString("address"));
                accident.setType(new AccidentType(rs.getInt(
                        "type_id"),
                        rs.getString("type_name")));
                accident.setRules(new HashSet<>());
                Rule rule = new Rule();
                rule.setId(rs.getInt("rule_id"));
                rule.setName(rs.getString("rule_name"));
                int id = accidentList.indexOf(accident);
                if (id == -1) {
                    accident.getRules().add(rule);
                    accidentList.add(accident);
                } else {
                    accidentList.get(id).getRules().add(rule);
                }
            }
            return accidentList;
        });
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID, ROW_MAPPER, id));
    }

    @Override
    @Transactional
    public void update(Accident accident) {
        jdbcTemplate.update(UPDATE,
                accident.getNumber(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        deleteAccidentRule(accident.getId());
        saveAccidentRule(new ArrayList<>(accident.getRules()), accident.getId());
    }

    private void saveAccidentRule(List<Rule> rules, int accidentId) {
        jdbcTemplate.batchUpdate(SET_ACCIDENT_RULE,
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Rule rule = rules.get(i);
                        ps.setInt(1, rule.getId());
                        ps.setInt(2, accidentId);
                    }

                    @Override
                    public int getBatchSize() {
                        return rules.size();
                    }
                });
    }

    private void deleteAccidentRule(int accidentId) {
        jdbcTemplate.update(DELETE_ACCIDENT_RULE, accidentId);
    }
}
