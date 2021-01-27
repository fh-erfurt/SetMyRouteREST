package de.SetMyRoute.controller;


import de.SetMyRoute.model.User;
import de.SetMyRoute.model.difficulty.Difficulty;
import de.SetMyRoute.model.difficulty.Signedness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public DifficultyRepository difficultyRepository;

    @GetMapping(path = "/users")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/users/{email}/{password}")
    public @ResponseBody
    Optional<User> getOneUserWithLogin(@PathVariable String email, @PathVariable String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPasswordHash().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    @GetMapping(value = "/users/{authToken}")
    public @ResponseBody
    Optional<User> getOneUserByAuthToken(@PathVariable String authToken) {
        return userRepository.findByAuthToken(authToken);
    }

    @PostMapping(path = "/user")
    public @ResponseBody
    String addUser(String email, String password, String firstName, String lastName, byte onSightDegreeValue, int signedness) {
        Optional<Difficulty> difficulty = difficultyRepository.findByValueAndSignedness(onSightDegreeValue, signedness);
        if(!difficulty.isPresent()) {
            Difficulty temp = new Difficulty(onSightDegreeValue, Signedness.fromInt(signedness));
            difficulty = Optional.of(temp);
            difficultyRepository.save(temp);
        }
        UUID authToken = UUID.randomUUID();
        User user = new User(firstName, lastName, email, difficulty.get(), authToken.toString(), password);
        userRepository.save(user);
        return authToken.toString();
    }

    @DeleteMapping("/user/{userId}")
    public @ResponseBody
    String deleteUser(@PathVariable Integer userId) {
        userRepository.deleteById(userId);
        return "Deleted";
    }


}
