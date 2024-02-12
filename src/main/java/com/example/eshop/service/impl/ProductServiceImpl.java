package com.example.eshop.service.impl;

import com.example.eshop.dto.product.ProductRequest;
import com.example.eshop.dto.product.ProductResponse;
import com.example.eshop.entities.Category;
import com.example.eshop.entities.Product;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.repository.CategoryRepository;
import com.example.eshop.repository.ProductRepository;
import com.example.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public void addProduct(ProductRequest productRequest, String token) {
        if(productRepository.findBySKU(productRequest.getSKU()).isPresent()){
            throw new NotFoundException("product with this SKU is already exist!: "+productRequest.getSKU(),
                    HttpStatus.BAD_REQUEST);
        }
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(product.getDescription());
        product.setSize(product.getSize());
        product.setColor(product.getColor());
        product.setSKU(product.getSKU());
        product.setPrice(product.getPrice());
        product.setDate(LocalDateTime.now().toString());
        product.setType(product.getType());
        product.setExist(true);
        Optional<Category> category = categoryRepository.findByName(productRequest.getCategory());
        if(category.isEmpty()) {
            throw new NotFoundException("No category with name: " + productRequest.getCategory(),HttpStatus.BAD_REQUEST);
        }
        product.setCategory(category.get());
        productRepository.save(product);

    }

    @Override
    public List<ProductResponse> getAll(String token) {
//        User user = authService.getUsernameFromToken(token);
//        if(!user.getRole().equals(Role.ADMIN)) {
//            System.out.println("user");
//            List<ProductResponse> phoneResponses = productMapper.toDtoS(productRepository.findAllByIsExist(true));
//            return phoneResponses;
//        }
//        return productMapper.toDtoS(productRepository.findAll());
        return null;
    }

    @Override
    public void buyProduct(Long productId, String token) {


    }
}
