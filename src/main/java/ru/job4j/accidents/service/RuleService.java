package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Rule;

public interface RuleService {
    Iterable<Rule> findAll();
}
