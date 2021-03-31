package de.SetMyRoute.controller;

import de.SetMyRoute.model.Route;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RouteRepository extends CrudRepository<Route, Integer> {
    Optional<Route> findByNameAndBuiltOn(String name, LocalDateTime date);
}
