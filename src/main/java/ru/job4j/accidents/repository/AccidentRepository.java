package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.List;

public interface AccidentRepository {

     void save(Accident accident);

     List<Accident> findAll();
}
