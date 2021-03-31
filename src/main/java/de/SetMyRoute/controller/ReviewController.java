package de.SetMyRoute.controller;

import de.SetMyRoute.model.Review;
import de.SetMyRoute.model.Route;
import de.SetMyRoute.model.User;
import de.SetMyRoute.model.difficulty.Difficulty;
import de.SetMyRoute.model.difficulty.Signedness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class ReviewController {

    @Autowired
    public ReviewRepository reviewRepository;
    @Autowired
    public RouteRepository routeRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public DifficultyRepository difficultyRepository;

    @GetMapping(value = "/review/{reviewID}")
    public @ResponseBody
    Optional<Review> getReviewFromRoute(@PathVariable Integer reviewID) {
        return reviewRepository.findById(reviewID);
    }

    @GetMapping(value = "/reviews/{routeID}")
    public @ResponseBody
    Iterable<Review> getAllReviewsFromRoute(@PathVariable Integer routeID) {
        Optional<Route> route = routeRepository.findById(routeID);
        return route.<Iterable<Review>>map(Route::getReviews).orElse(null);
    }

    @GetMapping(path = "/reviews")
    public @ResponseBody
    Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @DeleteMapping("/review/delete/{id}/{authToken}")
    public @ResponseBody
    String deleteReview(@PathVariable int id, @PathVariable String authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        Optional<Review> review = reviewRepository.findById(id);
        if(user.isPresent() && review.isPresent()) {
            if (user.get() == review.get().getAuthor() || user.get().isAdmin()) {
                reviewRepository.deleteById(id);
                return "Deleted";
            }
        }
        return "Not Allowed";
    }

    @PostMapping(path = "/review")
    public @ResponseBody
    String addReview( @RequestParam (name = "authToken") String authToken, @RequestParam  (name = "author") String author, @RequestParam (name = "headline") String headline, @RequestParam (name = "userDifficulty") String userDifficulty, @RequestParam (name = "like") String like, @RequestParam (name = "strongArm") String strongArm, @RequestParam (name = "longArm") String longArm, @RequestParam (name = "message") String message, @RequestParam  (name = "routeID") String routeID) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if(user.isPresent()) {
            String [] temp = userDifficulty.split("_");

            byte value = Byte.parseByte(temp[0]);
            if(!difficultyRepository.findByValueAndSignedness(value, Integer.parseInt(temp[1])).isPresent()){
                Difficulty difficulty = new Difficulty(value, Signedness.fromInt(Integer.parseInt(temp[1])));
                difficultyRepository.save(difficulty);
            }
            Review review = new Review(user.get(), headline, difficultyRepository.findByValueAndSignedness(value, Integer.parseInt(temp[1])).get(), Boolean.parseBoolean(like), Boolean.parseBoolean(strongArm), Boolean.parseBoolean(longArm), message);
            reviewRepository.save(review);
            int tempID = Integer.parseInt(routeID);
            routeRepository.findById(tempID).get().addReview(review);
            routeRepository.save(routeRepository.findById(tempID).get());
            return difficultyRepository.findByValueAndSignedness(value, Integer.parseInt(temp[1])).get().getId().toString();
        }
        return "Error 400";
    }
}
