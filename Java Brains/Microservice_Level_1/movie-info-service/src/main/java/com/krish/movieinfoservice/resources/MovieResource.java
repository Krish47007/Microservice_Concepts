package com.krish.movieinfoservice.resources;

import com.krish.movieinfoservice.models.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieResource {

    private List<Movie> movies = new ArrayList<>();

    @PostConstruct
    public void populateMovieList()
    {
        movies.add(new Movie("1000","Batman Origins","This movie deals with origin of Batman"));
        movies.add(new Movie("1001","Avengers : End Game","This movie is revenge against Thanos"));
        movies.add(new Movie("1002","Justice League","This movie unites all earth heroes"));

        movies.add(new Movie("1010","Star Trek","This movie deals with space travel and expansion of federation"));
        movies.add(new Movie("1011","John Wick","This is a classic action movie" ));
        movies.add(new Movie("1012","Dragon Ball Super : Broly","This movie shows the legendary super saiyan"));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity getMovieInfo(@PathVariable("movieId") String movieId)
    {
        for(Movie m : movies)
        {
            if(m.getMovieId().equals(movieId))
                return ResponseEntity.status(200).body(m);
        }

        return ResponseEntity.badRequest().body("Movie not found!!!");
    }
}
