package de.SetMyRoute.controller;

import de.SetMyRoute.model.Route;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends CrudRepository<Route, Integer> {
}
