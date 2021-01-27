package de.SetMyRoute.controller;

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

    @DeleteMapping("/difficulty/delete/{id}")
    public @ResponseBody
    String deleteDifficulty(@PathVariable int id) {
        difficultyRepository.deleteById(id);
        return "Deleted";
    }

    @PostMapping(path = "/difficulty")
    public @ResponseBody
    String addDifficulty(Difficulty difficulty) {
        difficultyRepository.save(difficulty);
        return "added";
    }

    @GetMapping(path = "/difficulty/{difficultyId}")
    public @ResponseBody
    Optional<Difficulty> findDifficultyById(@PathVariable Integer difficultyId) {
        return difficultyRepository.findById(difficultyId);
    }

    @GetMapping(path = "/difficulty")
    public @ResponseBody
    Iterable<Difficulty> findAllDifficultys() {
        return difficultyRepository.findAll();
    }
}
