package com.example.eshop.service.impl;

import com.example.eshop.dto.Comparison.CompareRequest;
import com.example.eshop.dto.Review.ReviewResponse;
import com.example.eshop.dto.product.ProductRequest;
import com.example.eshop.dto.product.ProductResponse;
import com.example.eshop.entities.Category;
import com.example.eshop.entities.Comparison;
import com.example.eshop.entities.Product;
import com.example.eshop.entities.User;
import com.example.eshop.exception.BadRequestException;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.mapper.ProductMapper;
import com.example.eshop.mapper.ReviewMapper;
import com.example.eshop.repository.CategoryRepository;
import com.example.eshop.repository.ComparisonRepository;
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
    private final ReviewMapper reviewMapper;
    private final ComparisonRepository comparisonRepository;


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
        product.setRating(productRequest.getRating());
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

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<ReviewResponse> comments(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        List<ReviewResponse> reviewResponses = reviewMapper.toDtoS(product.get().getProductReview());
        return reviewResponses;
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

    @Override
    public void deleteProduct(Long productId, String token) {
        User user = authService.getUsernameFromToken(token);

        if(user.getRole().equals(Role.Admin)) {
            productRepository.deleteById(productId);
        }
        else
            throw new NotFoundException("This function is available only for ADMIN", HttpStatus.BAD_REQUEST);

    }

    @Override
    public void updateById(Long productId, ProductRequest productRequest, String token) {
        User user = authService.getUsernameFromToken(token);

        if(user.getRole().equals(Role.Admin)) {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isEmpty())
                throw new NotFoundException("the product with id: " + productId + " is empty!", HttpStatus.BAD_REQUEST);
            product.get().setName(productRequest.getName());
            product.get().setDescription(productRequest.getDescription());
            product.get().setPrice(productRequest.getPrice());
            product.get().setColor(productRequest.getColor());
            product.get().setSKU(productRequest.getSKU());
            product.get().setDate(LocalDateTime.now().toString());
            Optional<Category> category = categoryRepository.findByName(productRequest.getCategory());
            if (category.isEmpty()) {
                throw new NotFoundException("No category with name: " + productRequest.getCategory(), HttpStatus.BAD_REQUEST);
            }
            product.get().setCategory(category.get());
            product.get().setExist(true);
            product.get().setSize(productRequest.getSize());

            if (!containsType(productRequest.getType()))
                throw new BadRequestException("no type with name: " + productRequest.getType() + "!");
            product.get().setType(Type.valueOf(productRequest.getType()));
            productRepository.save(product.get());
        }
        else
            throw new NotFoundException("This function is available only for ADMIN", HttpStatus.BAD_REQUEST);

    }

    @Override
    public void addFavoriteProduct(Long productId, String token) {
        User user = authService.getUsernameFromToken(token);
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()) {
            throw new NotFoundException("this product sold", HttpStatus.BAD_REQUEST);
        }
        List<Product> favoriteProducts = user.getCustomer().getFavoriteProducts();
//        if(!user.getCustomer().getFavoriteProducts().isEmpty()) {
//            favoriteProducts = user.getCustomer().getFavoriteProducts();
//        }
        favoriteProducts.add(product.get());
        if(favoriteProducts.contains(product.get()))
            throw new BadRequestException("This product already in favorites!");
        System.out.println("Add product " + product.get().getName());
        user.getCustomer().setFavoriteProducts(favoriteProducts);
//        System.out.println("add in list" );
        userRepository.save(user);
    }

    @Override
    public List<ProductResponse> getMyFavoriteProducts(String token) {
        User user = authService.getUsernameFromToken(token);
        if(!user.getRole().equals(Role.Admin)) {
            List<ProductResponse> favoriteProducts = productMapper.favoriteProducts(user.getCustomer().getFavoriteProducts());
            return favoriteProducts;
        }
        return null;
    }

    public void productcomparison(CompareRequest compareRequest, String token) {
        Comparison comparison = new Comparison();
        comparison.setSales_package(compareRequest.getSales_package());
        comparison.setModel_number(compareRequest.getModel_number());
        comparison.setSecondary_material(compareRequest.getSecondary_material());
        comparison.setConfiguration(compareRequest.getConfiguration());
        comparison.setUpholstery_material(compareRequest.getUpholstery_material());
        comparison.setUpholstery_color(compareRequest.getUpholstery_color());

        comparison.setFilling_material(compareRequest.getFilling_material());
        comparison.setFinish_type(compareRequest.getFinish_type());
        comparison.setAdjustable_headrest(compareRequest.getAdjustable_headrest());
        comparison.setMax_load_capacity(compareRequest.getMax_load_capacity());
        comparison.setOrigin_of_manufacture(compareRequest.getOrigin_of_manufacture());

        comparison.setWidth(compareRequest.getWidth());
        comparison.setHeight(compareRequest.getHeight());
        comparison.setDepth(compareRequest.getDepth());
        comparison.setWeight(compareRequest.getWeight());
        comparison.setSeat_height(compareRequest.getSeat_height());
        comparison.setLed_height(compareRequest.getLed_height());

        comparison.setWarranty_summary(compareRequest.getWarranty_summary());
        comparison.setWarranty_service_type(compareRequest.getWarranty_service_type());
        comparison.setCovered_in_warranty(compareRequest.getCovered_in_warranty());
        comparison.setNot_covered_in_warranty(compareRequest.getNot_covered_in_warranty());
        comparison.setDomestic_warranty(compareRequest.getDomestic_warranty());

        comparisonRepository.save(comparison);
    }

    @Override
    public Comparison compareproducts(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()) {
            throw new NotFoundException("Product not found", HttpStatus.BAD_GATEWAY);
        }
        return product.get().getComparison();
    }

    @Override
    public Comparison compareproducts2(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()) {
            throw new NotFoundException("Product not found", HttpStatus.BAD_GATEWAY);
        }
        return product.get().getComparison();
    }


    private boolean containsType(String type) {
        for (Type type1:Type.values()){
            if (type1.name().equalsIgnoreCase(type))
                return true;
        }
        return false;
    }
}
