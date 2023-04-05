package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.data.AccidentTypeRepository;

@Service
@AllArgsConstructor
public class SimpleAccidentTypeService implements AccidentTypeService {
    private AccidentTypeRepository accidentTypeRepository;

    @Override
    public Iterable<AccidentType> findAll() {
        return accidentTypeRepository.findAll();
    }
}
