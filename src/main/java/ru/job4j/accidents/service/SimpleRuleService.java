package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.data.RuleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SimpleRuleService implements  RuleService {
    private RuleRepository ruleRepository;

    @Override
    public List<Rule> findAll() {
        return ruleRepository.findAll();
    }
}
