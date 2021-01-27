package de.SetMyRoute.controller;

import de.SetMyRoute.model.Review;
import de.SetMyRoute.model.Route;
import de.SetMyRoute.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class RouteController {

    @Autowired
    public RouteRepository routeRepository;

    @GetMapping(path = "/route/{routeId}")
    public @ResponseBody
    Optional<Route> getRouteById(@PathVariable Integer routeId) {
        return routeRepository.findById(routeId);
    }

    @GetMapping(path = "/routes")
    public @ResponseBody
    Iterable<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @DeleteMapping("/route/{routeId}")
    public @ResponseBody
    String deleteRoute(@PathVariable Integer routeId) {
        routeRepository.deleteById(routeId);
        return "Deleted";
    }

    @PostMapping(path = "/route")
    public @ResponseBody
    String addRoute(Route route) {
        routeRepository.save(route);
        return "added";
    }
}
