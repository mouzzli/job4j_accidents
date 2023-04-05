package ru.job4j.accidents.repository.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Repository
public class HibernateRuleRepository implements RuleRepository {
    private final CrudRepository crudRepository;
    private static final String FIND_ALL = "FROM Rule";
    private static final String FIND_ACCIDENT_RULE = "FROM Rule "
            + "WHERE id IN :rId";

    @Override
    public List<Rule> findAll() {
        return crudRepository.query(FIND_ALL, Rule.class);
    }

    @Override
    public Set<Rule> findAccidentRules(List<Integer> rIds) {
        return new HashSet<>(crudRepository.query(FIND_ACCIDENT_RULE, Rule.class, Map.of("rId", rIds)));
    }
}
