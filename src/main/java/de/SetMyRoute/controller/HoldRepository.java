package de.SetMyRoute.controller;

import de.SetMyRoute.model.Hold;
import org.springframework.data.repository.CrudRepository;

public interface HoldRepository extends CrudRepository<Hold, Integer> {
}
