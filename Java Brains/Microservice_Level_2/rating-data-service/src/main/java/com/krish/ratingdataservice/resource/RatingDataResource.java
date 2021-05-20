package com.krish.ratingdataservice.resource;

import com.krish.ratingdataservice.models.Rating;
import com.krish.ratingdataservice.models.UserRating;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource {

    private Map<String, List<Rating>> userRatedMovies = new LinkedHashMap<>();

    @PostConstruct
    public void populateMovieItems()
    {
        //For user 1
        userRatedMovies.put("user1",
                Arrays.asList(
                        new Rating("100",9),
                        new Rating("101",8),
                        new Rating("102",9.2))
                );

        //For user 2
        userRatedMovies.put("user2",
                Arrays.asList(
                        new Rating("500",9.4),
                        new Rating("501",8.3),
                        new Rating("502",8.2))
        );
    }

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId)
    {
        return new Rating(movieId,8.0);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity getRatingsForUser(@PathVariable("userId") String userId)
    {
        List<Rating> ratingList = userRatedMovies.get(userId);

        if(ratingList == null)
            return ResponseEntity.badRequest().body("User doesn't exist");

        UserRating rating = new UserRating();
        rating.setUserRatings(ratingList);

        return ResponseEntity.status(200).body(rating);
    }
}
