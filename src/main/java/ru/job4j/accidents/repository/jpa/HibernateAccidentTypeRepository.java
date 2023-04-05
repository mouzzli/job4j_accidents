package ru.job4j.accidents.repository.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class HibernateAccidentTypeRepository implements AccidentTypeRepository {
    private final CrudRepository crudRepository;
    private static final String FIND_ALL = "FROM AccidentType";
    private static final String FIND_BY_ID = "FROM AccidentType "
            + "WHERE id = :id";

    @Override
    public List<AccidentType> findAll() {
        return crudRepository.query(FIND_ALL, AccidentType.class);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return crudRepository.optional(FIND_BY_ID, AccidentType.class, Map.of("id", id));
    }
}
