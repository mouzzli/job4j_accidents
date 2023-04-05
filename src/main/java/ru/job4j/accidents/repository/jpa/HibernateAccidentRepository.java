package ru.job4j.accidents.repository.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class HibernateAccidentRepository implements AccidentRepository {
    private final CrudRepository crudRepository;
    private static final String FIND_ALL = "SELECT DISTINCT a FROM Accident a "
            + "JOIN FETCH a.rules "
            + "ORDER BY a.id";
    private static final String FIND_BY_ID = "FROM Accident "
            + "WHERE id = :id";

    @Override
    public Accident save(Accident accident) {
        crudRepository.run(session -> session.save(accident));
        return accident;
    }

    @Override
    public List<Accident> findAll() {
        return crudRepository.query(FIND_ALL, Accident.class);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return crudRepository.optional(FIND_BY_ID, Accident.class, Map.of("id", id));
    }

    @Override
    public void update(Accident accident) {
        crudRepository.run(session -> session.merge(accident));
    }
}
