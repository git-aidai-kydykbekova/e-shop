package com.example.eshop.service.impl;

import com.example.eshop.dto.product.ProductRequest;
import com.example.eshop.dto.product.ProductResponse;
import com.example.eshop.entities.Category;
import com.example.eshop.entities.Product;
import com.example.eshop.entities.User;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.mapper.ProductMapper;
import com.example.eshop.repository.CategoryRepository;
import com.example.eshop.repository.ProductRepository;
import com.example.eshop.repository.UserRepository;
import com.example.eshop.role.Role;
import com.example.eshop.role.Type;
import com.example.eshop.service.AuthService;
import com.example.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AuthService authService;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;


    @Override
    public void addProduct(ProductRequest productRequest, String token) {
        if(productRepository.findBySKU(productRequest.getSKU()).isPresent()){
            throw new NotFoundException("product with this SKU is already exist!: "+productRequest.getSKU(),
                    HttpStatus.BAD_REQUEST);
        }
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setSize(productRequest.getSize());
        product.setColor(productRequest.getColor());
        product.setSKU(productRequest.getSKU());
        product.setPrice(productRequest.getPrice());
        product.setDate(LocalDateTime.now().toString());
        product.setType(Type.valueOf(productRequest.getType()));
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
        User user = authService.getUsernameFromToken(token);
        if(!user.getRole().equals(Role.Admin)) {
            System.out.println("user");
            List<ProductResponse> phoneResponses = productMapper.toDtoS(productRepository.findAllByIsExist(true));
            return phoneResponses;
        }
        return productMapper.toDtoS(productRepository.findAll());

    }

    @Override
    public void buyProduct(Long productId, String token) {
        User user = authService.getUsernameFromToken(token);
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()) {
            throw new NotFoundException("this product sold", HttpStatus.BAD_REQUEST);
        }
        product.get().setExist(false);
        List<Product>products = new ArrayList<>();
        if(!user.getCustomer().getProducts().isEmpty()) {
            products = user.getCustomer().getProducts();
        }
        products.add(product.get());
        user.getCustomer().setProducts(products);

        userRepository.save(user);

    }

    @Override
    public List<ProductResponse> getMyProducts(String token) {
        User user = authService.getUsernameFromToken(token);
        if(!user.getRole().equals(Role.Admin)) {
            List<ProductResponse> productResponses = productMapper.toDtoS(user.getCustomer().getProducts());
            return productResponses;
        }
        return null;
    }
}
