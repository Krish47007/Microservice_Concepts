package com.krish.moviecatalogservice.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    private String movieId;
    private double rating;
}
