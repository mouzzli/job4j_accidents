package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.data.AccidentRepository;
import ru.job4j.accidents.repository.data.AccidentTypeRepository;
import ru.job4j.accidents.repository.data.RuleRepository;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {
    private AccidentRepository accidentRepository;
    private AccidentTypeRepository accidentTypeRepository;
    private RuleRepository ruleRepository;

    @Override
    public void save(Accident accident, List<Integer> rIds) {
        accident.setType(accidentTypeRepository.findById(accident.getType().getId()).get());
        accident.setRules(new HashSet<>((Collection) ruleRepository.findAllById(rIds)));
        accidentRepository.save(accident);
    }

    @Override
    public void update(Accident accident, List<Integer> rIds) {
        accident.setType(accidentTypeRepository.findById(accident.getType().getId()).get());
        accident.setRules(new HashSet<>((Collection) ruleRepository.findAllById(rIds)));
        accidentRepository.save(accident);
    }

    @Override
    public Iterable<Accident> findAll() {
        return accidentRepository.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }
}
