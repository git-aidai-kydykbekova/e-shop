package com.example.eshop.service;

import com.example.eshop.dto.Review.ReviewRequest;
import com.example.eshop.entities.Review;

public interface ReviewService {

    void addReview(ReviewRequest reviewRequest, String token, Long productId);
}