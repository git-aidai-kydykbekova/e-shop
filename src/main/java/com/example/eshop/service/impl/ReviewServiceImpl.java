package com.example.eshop.service.impl;

import com.example.eshop.dto.Review.ReviewRequest;
import com.example.eshop.entities.Product;
import com.example.eshop.entities.Review;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.repository.ProductRepository;
import com.example.eshop.repository.ReviewRepository;

import com.example.eshop.service.ProductService;
import com.example.eshop.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Override
    public void addReview(ReviewRequest reviewRequest, String token, Long productId) {
        // Получаем продукт по его идентификатору
        Product product = productService.getProductById(productId);

        if (product != null) {
            // Создаем новый отзыв
            Review review = new Review();

            // Устанавливаем поля отзыва из запроса
            review.setRating(reviewRequest.getRating());
            review.setComment(reviewRequest.getComment());

            // Устанавливаем продукт для отзыва
            review.setProduct(product);

            // Сохраняем отзыв в базе данных
            reviewRepository.save(review);

            if(product.getRating() != null) {
                int totalReviews = product.getTotalreview();
                double existingRating = product.getRating();
                double newRating = ((existingRating * totalReviews) + reviewRequest.getRating()) / (totalReviews + 1);
                product.setTotalreview(totalReviews + 1);
                product.setRating(newRating);
                productRepository.save(product);
            }
            else {
                product.setTotalreview(1);
                product.setRating(reviewRequest.getRating());
                productRepository.save(product);
            }
            // Обновляем рейтинг и количество отзывов продукта

            // Вычисляем новый рейтинг

            // Сохраняем обновленные данные о продукте

        } else {
            // Если продукт не найден, выбрасываем исключение или делаем другие действия, в зависимости от вашего требования
            throw new NotFoundException("Product with Id " + productId + " is not found", HttpStatus.BAD_REQUEST);
        }
    }
}
