package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentService {

    void save(Accident accident, List<Integer> rIds);

    void update(Accident accident, List<Integer> rIds);

    Iterable<Accident> findAll();

    Optional<Accident> findById(int id);
}
