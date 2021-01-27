package de.SetMyRoute.controller;

import de.SetMyRoute.model.RouteColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class RoutecolorController {

    @Autowired
    public RoutecolorRepository routecolorRepository;


    @DeleteMapping("/routecolor/delete/{routecolorId}")
    public @ResponseBody
    String deleteRouteColor(@PathVariable int routecolorId) {
        routecolorRepository.deleteById(routecolorId);
        return "Deleted";
    }

    @PostMapping(path = "/routecolor")
    public @ResponseBody
    String addRouteColor(RouteColor routeColor) {
        routecolorRepository.save(routeColor);
        return "added";
    }

    @GetMapping(path = "/routecolor/{routecolorId}")
    public @ResponseBody
    Optional<RouteColor> findRouteColorById(@PathVariable Integer routecolorId) {
        return routecolorRepository.findById(routecolorId);
    }

    @GetMapping(path = "/routecolor")
    public @ResponseBody
    Iterable<RouteColor> findAllRouteColors() {
        return routecolorRepository.findAll();
    }
}
