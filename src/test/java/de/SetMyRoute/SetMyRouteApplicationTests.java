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
        Route route = new Route("Die Harte", "ja", one, "markus", LocalDateTime.now(), reviews1, red);
        routeRepository.save(route);
        Route route2 = new Route("Die Hasafarte", "ja", one, "markuasdass", LocalDateTime.now(),reviews2, blue);
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
    @Test
    void addRoutes()
    {
        Difficulty one =  new Difficulty((byte) 6, Signedness.NEGATIVE);
        Difficulty two = new Difficulty((byte) 9, Signedness.NEGATIVE);
        Difficulty three = new Difficulty((byte) 7, Signedness.POSITIVE);
        Difficulty four = new Difficulty((byte) 4, Signedness.NONE);
        Difficulty five = new Difficulty((byte) 3, Signedness.NEGATIVE);
        Difficulty six = new Difficulty((byte) 7, Signedness.NEGATIVE);
        Difficulty seven =  new Difficulty((byte) 9, Signedness.NONE);
        Difficulty eight = new Difficulty((byte) 10, Signedness.NEGATIVE);
        Difficulty nine = new Difficulty((byte) 3, Signedness.POSITIVE);
        Difficulty ten = new Difficulty((byte) 4, Signedness.NONE);
        difficultyRepository.save(one);
        difficultyRepository.save(two);
        difficultyRepository.save(three);
        difficultyRepository.save(four);
        difficultyRepository.save(five);
        difficultyRepository.save(six);
        difficultyRepository.save(seven);
        difficultyRepository.save(eight);
        difficultyRepository.save(nine);
        difficultyRepository.save(ten);
        RouteColor grau = new RouteColor(Color.GRAY.getRGB(), "Grau");
        RouteColor blau = new RouteColor(Color.BLUE.getRGB(), "Blau");
        RouteColor rot = new RouteColor(Color.RED.getRGB(), "Rot");
        RouteColor gruen = new RouteColor(Color.GREEN.getRGB(), "Grün");
        RouteColor gelb = new RouteColor(Color.YELLOW.getRGB(), "Gelb");
        routecolorRepository.save(grau);
        routecolorRepository.save(blau);
        routecolorRepository.save(rot);
        routecolorRepository.save(gruen);
        routecolorRepository.save(gelb);
        List<Review> reviews1 = new ArrayList<>();
        List<Review> reviews2 = new ArrayList<>();
        List<Review> reviews3 = new ArrayList<>();
        List<Review> reviews4 = new ArrayList<>();
        List<Review> reviews5 = new ArrayList<>();
        List<Review> reviews6 = new ArrayList<>();
        List<Review> reviews7 = new ArrayList<>();
        List<Review> reviews8 = new ArrayList<>();
        List<Review> reviews9 = new ArrayList<>();
        List<Review> reviews10 = new ArrayList<>();
        Hold hold1 = new Hold("Crux", "round");
        Hold hold2 = new Hold("Bluestone", "abstract");
        Hold hold3 = new Hold("Entre Prie", "difficult");
        Hold hold4 = new Hold("Crux", "triangle");
        Hold hold5 = new Hold("Bluestone", "difficult");
        Hold hold6 = new Hold("Entre Prie", "round");
        Hold hold7 = new Hold("Crux", "cube");
        Hold hold8 = new Hold("Bluestone", "slippery");
        holdRepository.save(hold1);
        holdRepository.save(hold2);
        holdRepository.save(hold3);
        holdRepository.save(hold4);
        holdRepository.save(hold5);
        holdRepository.save(hold6);
        holdRepository.save(hold7);
        holdRepository.save(hold8);
        Route route1 = new Route( "Technik ist gefragt!", "22b", one, "Falk Hoch", LocalDateTime.now(), reviews1,grau);
        Route route2 = new Route( "Tanz der Amygdalla", "13a", three, "Niclas Tief", LocalDateTime.now(), reviews2,blau);
        Route route3 = new Route( "Route 5", "12b", two, "David Pabst", LocalDateTime.now(),reviews3,rot);
        Route route4 = new Route( "Grüne Hölle", "24b", four, "Andi hinten", LocalDateTime.now(),reviews4,gruen);
        Route route5 = new Route( "Ohne Technik fällts", "2a", five, "Ralf wegda", LocalDateTime.now(), reviews5,grau);
        Route route6 = new Route( "Adrian machts", "5a", six, "Anni Klauts", LocalDateTime.now(), reviews7,gelb);
        Route route7 = new Route( "Ab Hoch!", "7b",seven, "Falk Hoch", LocalDateTime.now(), reviews8,rot);
        Route route8 = new Route( "Vorsicht Freifall", "11b", eight, "Andi hinten", LocalDateTime.now(), reviews9 ,gruen);
        Route route9 = new Route( "Route 9", "15b", nine, "Anni Klauts", LocalDateTime.now(),reviews6,rot);
        Route route10 = new Route( "Keine Idee", "22a", ten, "Niclas Tief", LocalDateTime.now(), reviews10,gelb);
        routeRepository.save(route1);
        routeRepository.save(route2);
        routeRepository.save(route3);
        routeRepository.save(route4);
        routeRepository.save(route5);
        routeRepository.save(route6);
        routeRepository.save(route7);
        routeRepository.save(route8);
        routeRepository.save(route9);
        routeRepository.save(route10);
    }
}
