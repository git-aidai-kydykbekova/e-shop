package com.example.eshop.dto.order;

import com.example.eshop.dto.image.ImageResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter

public class OrderDetailResponse {
    private Long id;
    private LocalDateTime createDate;
    private String title;
    private ImageResponse image;
    private Integer price;
    private Integer total;
    private Integer quantity;
    private String sku;
}
