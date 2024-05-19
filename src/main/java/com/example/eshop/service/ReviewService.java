package com.example.eshop.service;

import com.example.eshop.dto.Review.ReviewRequest;
import com.example.eshop.dto.Review.ReviewResponse;


import java.util.List;

public interface ReviewService {

    void addReview(ReviewRequest reviewRequest, String token, Long productId);

    List<ReviewResponse> getReviews(Long productId);

    void deleteReview(Long id, String token);
}