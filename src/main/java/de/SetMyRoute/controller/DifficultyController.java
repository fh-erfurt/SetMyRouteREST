package de.SetMyRoute.controller;

import de.SetMyRoute.model.User;
import de.SetMyRoute.model.difficulty.Difficulty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class DifficultyController
{
    @Autowired
    public DifficultyRepository difficultyRepository;

    @Autowired
    public UserRepository userRepository;

    @DeleteMapping("/difficulty/delete/{id}/{authToken}")
    public @ResponseBody
    String deleteDifficulty(@PathVariable int id, @PathVariable String authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if(user.isPresent()) {
            if (user.get().isAdmin()) {
                difficultyRepository.deleteById(id);
                return "Deleted";
            }
        }
        return "Not allowed!";
    }

    @PostMapping(path = "/difficulty/{authToken}")
    public @ResponseBody
    String addDifficulty(Difficulty difficulty, @PathVariable String authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if(user.isPresent()) {
            if (user.get().isAdmin()) {
                difficultyRepository.save(difficulty);
                return "added";
            }
        }
        return "Not allowed!";
    }

    @GetMapping(path = "/difficulty/{difficultyId}")
    public @ResponseBody
    Optional<Difficulty> findDifficultyById(@PathVariable Integer difficultyId) {
        return difficultyRepository.findById(difficultyId);
    }

    @GetMapping(path = "/difficultys")
    public @ResponseBody
    Iterable<Difficulty> findAllDifficultys() {
        return difficultyRepository.findAll();
    }
}
