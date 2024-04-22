package com.example.eshop.mapper;

import com.example.eshop.dto.image.ImageResponse;
import com.example.eshop.entities.Image;

public interface ImageMapper {
    ImageResponse toDto(Image image);

    ImageResponse toDetailDto(Image image);
}
