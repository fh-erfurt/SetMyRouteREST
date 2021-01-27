package de.SetMyRoute.controller;


import de.SetMyRoute.model.difficulty.Difficulty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DifficultyRepository extends CrudRepository<Difficulty, Integer> {

    @Query(value = "SELECT * FROM Difficulty d WHERE d.value = ?1 AND d.signedness = ?2", nativeQuery = true)
    Optional<Difficulty> findByValueAndSignedness(byte value, int signedness);

}
