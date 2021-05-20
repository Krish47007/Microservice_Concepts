package com.krish.ratingdataservice.models;

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
