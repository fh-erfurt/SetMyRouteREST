package de.SetMyRoute.controller;

import de.SetMyRoute.model.RouteColor;
import org.springframework.data.repository.CrudRepository;

import java.awt.*;
import java.util.Optional;

public interface RoutecolorRepository extends CrudRepository<RouteColor, Integer> {
    Optional<RouteColor> findByColor(int color);
}
