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

    @Autowired
    public UserRepository userRepository;

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

    @DeleteMapping("/route/{routeId}/{authToken}")
    public @ResponseBody
    String deleteRoute(@PathVariable Integer routeId, @PathVariable String authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if(user.isPresent()) {
            if (user.get().isAdmin()) {
                routeRepository.deleteById(routeId);
                return "Deleted";
            }
        }
        return "Not allowed!";
    }

    @PostMapping(path = "/route/{authToken}")
    public @ResponseBody
    String addRoute(Route route, @PathVariable String authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if(user.isPresent()) {
            if (user.get().isAdmin()) {
                routeRepository.save(route);
                return "added";
            }
        }
        return "Error 400";
    }
}
