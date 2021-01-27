package de.SetMyRoute.controller;

import de.SetMyRoute.model.Review;
import de.SetMyRoute.model.Route;
import de.SetMyRoute.model.User;
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

    @GetMapping(value = "/review/{routeID}")
    public @ResponseBody
    Optional<Review> getReviewFromRoute(@PathVariable Integer routeID) {

        return reviewRepository.findById(routeID);
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

    @PostMapping(path = "/review/{authToken}")
    public @ResponseBody
    String addReview(Review review, @PathVariable String authToken) {
        Optional<User> user = userRepository.findByAuthToken(authToken);
        if(user.isPresent()) {
            reviewRepository.save(review);
            return "added";
        }
        return "Error 400";
    }
}
