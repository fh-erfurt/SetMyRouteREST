package de.SetMyRoute.controller;

import de.SetMyRoute.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository  extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByAuthToken(String authToken);
}
