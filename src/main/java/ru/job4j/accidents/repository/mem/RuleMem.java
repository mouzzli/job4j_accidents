package ru.job4j.accidents.repository.mem;

import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RuleMem implements RuleRepository {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleMem() {
        rules.put(1, new Rule(1, "Статья. 1"));
        rules.put(2, new Rule(2, "Статья. 2"));
        rules.put(3, new Rule(3, "Статья. 3"));
    }

    @Override
    public List<Rule> findAll() {
        return new ArrayList<>(rules.values());
    }

    @Override
    public Set<Rule> findAccidentRules(List<Integer> rIds) {
        Set<Rule> result = new HashSet<>();
        for (Integer rId : rIds) {
            Rule rule = rules.get(rId);
            if (rule != null) {
                result.add(rule);
            }
        }
        return result;
    }
}
