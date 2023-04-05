package ru.job4j.accidents.service;

import ru.job4j.accidents.model.AccidentType;

public interface AccidentTypeService {
    Iterable<AccidentType> findAll();
}