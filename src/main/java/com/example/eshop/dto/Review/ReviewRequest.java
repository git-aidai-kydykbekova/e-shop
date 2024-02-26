package com.example.eshop.dto.Review;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewRequest {

    private Double rating;
    private String comment;
}
