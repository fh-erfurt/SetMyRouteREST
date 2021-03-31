package de.SetMyRoute.controller;

import de.SetMyRoute.model.Review;
import de.SetMyRoute.model.Route;
import de.SetMyRoute.model.RouteColor;
import de.SetMyRoute.model.User;
import de.SetMyRoute.model.difficulty.Difficulty;
import de.SetMyRoute.model.difficulty.Signedness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api")
public class RouteController {

    @Autowired
    public RouteRepository routeRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public DifficultyRepository difficultyRepository;

    @Autowired
    public  RoutecolorRepository routecolorRepository;

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

    @PostMapping(path = "/route")
    public @ResponseBody
    String addRoute(@RequestParam(name = "authToken") String authToken,@RequestParam(name = "name") String name, @RequestParam(name = "segment") String segment, @RequestParam (name = "userDifficulty") String userDifficulty, @RequestParam (name = "setter") String setter, @RequestParam (name = "builtOn") String builtOn, @RequestParam(name = "color") String color, @RequestParam(name = "colorName") String colorName) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if(user.isPresent()) {
            if (user.get().isAdmin()) {
                String [] temp = userDifficulty.split("_");
                byte value = Byte.parseByte(temp[0]);
                if(!routecolorRepository.findByColor(Integer.parseInt(color)).isPresent()){
                    RouteColor routeColor = new RouteColor(Integer.parseInt(color), colorName);
                    routecolorRepository.save(routeColor);
                }
                if(!difficultyRepository.findByValueAndSignedness(value, Integer.parseInt(temp[1])).isPresent()){
                    Difficulty difficulty = new Difficulty(value, Signedness.fromInt(Integer.parseInt(temp[1])));
                    difficultyRepository.save(difficulty);
                }
                List<Review> emptyList = new ArrayList<>();
                Route route = new Route(name, segment, difficultyRepository.findByValueAndSignedness(value, Integer.parseInt(temp[1])).get(), setter, LocalDateTime.parse(builtOn), emptyList, routecolorRepository.findByColor(Integer.parseInt(color)).get());
                routeRepository.save(route);
                return routeRepository.findByNameAndBuiltOn(name, LocalDateTime.parse(builtOn)).get().getId().toString();
            }
        }
        return "Error 400";
    }
}
