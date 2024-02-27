package com.example.eshop.mapper.impl;

import com.example.eshop.dto.Review.ReviewResponse;
import com.example.eshop.entities.Review;
import com.example.eshop.mapper.ReviewMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public List<ReviewResponse> toDtoS(List<Review> all) {
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for(Review review: all) {
            reviewResponses.add(toDto(review));
        }
        return reviewResponses;
    }

    public ReviewResponse toDto(Review review) {

        ReviewResponse reviewResponse = new ReviewResponse();

        reviewResponse.setComment(review.getComment());

        return reviewResponse;
    }
}
