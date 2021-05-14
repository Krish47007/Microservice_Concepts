package com.krish.moviecatalogservice.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogItem {

    private String movieName;
    private String description;
    private double rating;

}
