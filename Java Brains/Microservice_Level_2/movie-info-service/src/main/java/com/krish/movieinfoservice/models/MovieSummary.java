package com.krish.movieinfoservice.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieSummary {

    private String original_title;
    private String overview;
    private double rating;
}
