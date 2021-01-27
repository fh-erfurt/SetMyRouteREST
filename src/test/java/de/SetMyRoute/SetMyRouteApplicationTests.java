package de.SetMyRoute;

import de.SetMyRoute.controller.*;
import de.SetMyRoute.model.*;
import de.SetMyRoute.model.difficulty.Difficulty;
import de.SetMyRoute.model.difficulty.Signedness;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class SetMyRouteApplicationTests
{

    @Autowired
    public RoutecolorRepository routecolorRepository;
    @Autowired
    public DifficultyRepository difficultyRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public RouteRepository routeRepository;
    @Autowired
    public ReviewRepository reviewRepository;
    @Autowired
    public HoldRepository holdRepository;

    @Test
    void contextLoads()
    {
        Difficulty one = new Difficulty((byte) 1, Signedness.NONE);
        difficultyRepository.save(one);
        Difficulty two = new Difficulty((byte) 5, Signedness.POSITIVE);
        difficultyRepository.save(two);
        User user = new User("Antomn", "Bespalov", "asbadb@fsd.de", one, UUID.randomUUID().toString(), User.hashPassword("anton"));
        user = userRepository.save(user);
        User user2 = new User("Antomssn", "Bespalov", "asbfsdfsdadb@fsd.de", two, UUID.randomUUID().toString(), User.hashPassword("anton"));
        userRepository.save(user2);
        List<Hold> list1 = new ArrayList<>();
        List<Hold> list2 = new ArrayList<>();
        Hold hold1 = new Hold("markus", "difficult");
        holdRepository.save(hold1);
        Hold hold2 = new Hold("markussdf", "difficsdfult");
        holdRepository.save(hold2);
        List<Review> reviews1 = new ArrayList<>();
        List<Review> reviews2 = new ArrayList<>();
        Review review1 = new Review(user, "SehrCool",one, true, true, true, "SehrCooleStrecke");
        reviewRepository.save(review1);
        Review review2 = new Review(user, "SehrCool1", one, true, true, true, "SehrCooleStreckem");
        reviewRepository.save(review2);
        Review review3 = new Review(user, "SehrCoolsd1", one, true, true, true, "SehrCooleStresdfsckem");
        reviewRepository.save(review3);
        reviews1.add(review1);
        reviews1.add(review2);
        reviews2.add(review3);
        RouteColor red = new RouteColor(Color.red.getRGB(), "Rot");
        routecolorRepository.save(red);
        RouteColor blue = new RouteColor(Color.blue.getRGB(), "Blau");
        routecolorRepository.save(blue);
        Route route = new Route("Die Harte", "ja", one, "markus", LocalDateTime.now(), hold1, reviews1, red);
        routeRepository.save(route);
        Route route2 = new Route("Die Hasafarte", "ja", one, "markuasdass", LocalDateTime.now(), hold2, reviews2, blue);
        routeRepository.save(route2);
    }

    @Test
    void resetPasswordAdrian()
    {

	    final Optional<User> optionalUser = userRepository.findByEmail("adrian.petzold@fh-erfurt.de");
	    if(optionalUser.isPresent()){
		    User user = optionalUser.get();
		    user.setPasswordHash(User.hashPassword("asdf123"));

		    userRepository.save(user);
	    }
    }
}
