package com.krish.moviecatalogservice.resources;

import com.krish.moviecatalogservice.models.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String showHome()
    {
        return "Welcome to Movie Catalog service";
    }

    @GetMapping("/{userId}")
    public ResponseEntity getCatalog(@PathVariable("userId") String userId)
    {
       try
       {
           //Get all movie Ids
           UserRating userRating = restTemplate.getForObject("http://localhost:8086/ratingsdata/users/"+userId, UserRating.class);


           //For each movie id, call movie-info service and get details
           //Convert it to CatalogItem and send response
           List<CatalogItem> catalogsForUser =  userRating.getUserRatings().stream().map(r->{
               Movie m = restTemplate.getForObject("http://localhost:8084/movies/"+r.getMovieId(),Movie.class);
               return new CatalogItem(m.getMovieName(),m.getDescription(),r.getRating());
           }).collect(Collectors.toList());

           UserCatalog  userCatalog = new UserCatalog();
           userCatalog.setCatalogItems(catalogsForUser);

           return ResponseEntity.status(200).body(userCatalog);
       }
       catch (Exception e)
       {
           return ResponseEntity.badRequest().body(e.getMessage());
       }

    }
}
