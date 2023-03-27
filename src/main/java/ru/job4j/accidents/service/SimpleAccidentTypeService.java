package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SimpleAccidentTypeService implements AccidentTypeService {
    private AccidentTypeRepository accidentTypeRepository;

    @Override
    public List<AccidentType> findAll() {
        return accidentTypeRepository.findAll();
    }
}
