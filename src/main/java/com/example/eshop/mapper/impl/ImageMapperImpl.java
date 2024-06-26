package com.example.eshop.mapper.impl;

import com.example.eshop.dto.image.ImageResponse;
import com.example.eshop.entities.Image;
import com.example.eshop.mapper.ImageMapper;
import org.springframework.stereotype.Component;

@Component
public class ImageMapperImpl implements ImageMapper {
    @Override
    public ImageResponse toDto(Image image) {

        ImageResponse imageResponse = new ImageResponse();

        imageResponse.setId(image.getId());
        imageResponse.setPath(image.getPath());
        imageResponse.setName(image.getName());
        imageResponse.setProductId(image.getProduct().getId());

        return imageResponse;
    }

    @Override
    public ImageResponse toDetailDto(Image image) {
        ImageResponse response = new ImageResponse();
        response.setId(image.getId());
        response.setPath(image.getPath());
        response.setName(image.getName());
        response.setProductId(image.getProduct().getId());
        return response;

    }
}
