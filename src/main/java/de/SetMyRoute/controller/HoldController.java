package de.SetMyRoute.controller;

import de.SetMyRoute.model.Hold;
import de.SetMyRoute.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class HoldController {

    @Autowired
    public HoldRepository holdRepository;

    @Autowired
    public UserRepository userRepository;


    @DeleteMapping("/hold/delete/{holdId}/{authToken}")
    public @ResponseBody
    String deleteHold(@PathVariable int holdId, @PathVariable String authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if(user.isPresent()) {
            if (user.get().isAdmin()) {
                holdRepository.deleteById(holdId);
                return "Deleted";
            }
        }
        return "Not allowed!";
    }

    @PostMapping(path = "/hold/{authToken}")
    public @ResponseBody
    String addHold(Hold hold, @PathVariable String authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if(user.isPresent()) {
            if (user.get().isAdmin()) {
                holdRepository.save(hold);
                return "added";
            }
        }
        return "Not allowed!";
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
