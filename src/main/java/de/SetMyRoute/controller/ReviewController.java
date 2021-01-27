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

    @DeleteMapping("/review/delete/{id}")
    public @ResponseBody
    String deleteReview(@PathVariable int id) {
        reviewRepository.deleteById(id);
        return "Deleted";
    }

    @PostMapping(path = "/review")
    public @ResponseBody
    String addReview(Review review) {
        reviewRepository.save(review);
        return "added";
    }
}
