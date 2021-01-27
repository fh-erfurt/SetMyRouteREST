package de.SetMyRoute.controller;


import de.SetMyRoute.model.difficulty.Difficulty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DifficultyRepository extends CrudRepository<Difficulty, Integer> {

    @Query(value = "select d from Difficulty where d.value = ?1 and d.signedness = ?2;", nativeQuery = true)
    Optional<Difficulty> findByValueAndSignedness(byte value, int signedness);

}
