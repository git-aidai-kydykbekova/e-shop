package com.example.eshop.controller;
import com.example.eshop.dto.Review.ReviewRequest;
import com.example.eshop.dto.Review.ReviewResponse;
import com.example.eshop.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add/{productId}")
    public void addReview(@RequestBody ReviewRequest reviewRequest, @RequestHeader("Authorization") String token, @PathVariable Long productId) {
        reviewService.addReview(reviewRequest, token, productId);
    }
    @GetMapping("/list/review/{productId}")
    public List<ReviewResponse> getReviews(@PathVariable Long productId) {
        return reviewService.getReviews(productId);
    }

}
