package com.example.eshop.mapper;

import com.example.eshop.dto.Review.ReviewResponse;
import com.example.eshop.entities.Review;

import java.util.List;

public interface ReviewMapper {

    List<ReviewResponse> toDtoS(List<Review> all);
}
