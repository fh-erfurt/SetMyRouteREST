package de.SetMyRoute.controller;

import de.SetMyRoute.model.Hold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class HoldController {

    @Autowired
    public HoldRepository holdRepository;


    @DeleteMapping("/hold/delete/{holdId}")
    public @ResponseBody
    String deleteHold(@PathVariable int holdId) {
        holdRepository.deleteById(holdId);
        return "Deleted";
    }

    @PostMapping(path = "/hold")
    public @ResponseBody
    String addHold(Hold hold) {
        holdRepository.save(hold);
        return "added";
    }

    @GetMapping(path = "/hold/{holdId}")
    public @ResponseBody
    Optional<Hold> findHoldById(@PathVariable Integer holdId) {
        return holdRepository.findById(holdId);
    }

    @GetMapping(path = "/holds")
    public @ResponseBody
    Iterable<Hold> findAllHolds() {
        return holdRepository.findAll();
    }
}
