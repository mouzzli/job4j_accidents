package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentRepository {
    private final AtomicInteger accidentCount;
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        Set<Rule> rules1 = Set.of(new Rule(1, "Статья. 1"), new Rule(2, "Статья. 2"));
        Set<Rule> rules2 = Set.of(new Rule(1, "Статья. 1"), new Rule(3, "Статья. 3"));
        accidents.put(1, new Accident(1, "А784МУ", "Превышение скорости", "Воронежская область, город Дмитров, пр. Ломоносова, 54", new AccidentType(4, "Одна Машина"), rules1));
        accidents.put(2, new Accident(2, "Т737СС", "Проезд на красный свет", "Амурская область, город Луховицы, проезд Ленина, 96", new AccidentType(4, "Одна Машина"), rules2));
        accidentCount = new AtomicInteger(accidents.size());
    }

    @Override
    public void save(Accident accident) {
        accident.setId(accidentCount.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    @Override
    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    @Override
    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }
}
