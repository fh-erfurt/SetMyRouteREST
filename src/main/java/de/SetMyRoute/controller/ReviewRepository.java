package de.SetMyRoute.controller;

import de.SetMyRoute.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Integer>{
}
