package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentRepository {
    private final AtomicInteger contId;
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident(1, "А784МУ", "Превышение скорости", "Воронежская область, город Дмитров, пр. Ломоносова, 54"));
        accidents.put(2, new Accident(2, "Т737СС", "Проезд на красный свет", "Амурская область, город Луховицы, проезд Ленина, 96"));
        contId = new AtomicInteger(accidents.size());
    }

    @Override
    public void save(Accident accident) {
        accident.setId(contId.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }
}
