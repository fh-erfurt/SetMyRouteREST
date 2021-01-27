package de.SetMyRoute.controller;

import de.SetMyRoute.model.RouteColor;
import de.SetMyRoute.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class RoutecolorController {

    @Autowired
    public RoutecolorRepository routecolorRepository;

    @Autowired
    public UserRepository userRepository;


    @DeleteMapping("/routecolor/delete/{routecolorId}/{authToken}")
    public @ResponseBody
    String deleteRouteColor(@PathVariable int routecolorId, @PathVariable String authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if(user.isPresent()) {
            if (user.get().isAdmin()) {
                routecolorRepository.deleteById(routecolorId);
                return "Deleted";
            }
        }
        return "Not allowed!";
    }

    @PostMapping(path = "/routecolor/{authToken}")
    public @ResponseBody
    String addRouteColor(RouteColor routeColor, @PathVariable String authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if(user.isPresent()) {
            if (user.get().isAdmin()) {
                routecolorRepository.save(routeColor);
                return "added";
            }
        }
        return "Not allowed!";
    }

    @GetMapping(path = "/routecolor/{routecolorId}")
    public @ResponseBody
    Optional<RouteColor> findRouteColorById(@PathVariable Integer routecolorId) {
        return routecolorRepository.findById(routecolorId);
    }

    @GetMapping(path = "/routecolors")
    public @ResponseBody
    Iterable<RouteColor> findAllRouteColors() {
        return routecolorRepository.findAll();
    }
}
