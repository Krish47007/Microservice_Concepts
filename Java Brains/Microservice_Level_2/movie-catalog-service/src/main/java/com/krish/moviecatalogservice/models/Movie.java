package com.krish.moviecatalogservice.models;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private String movieId;
    private String movieName;
    private String description;

}
