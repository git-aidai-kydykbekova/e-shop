package com.example.eshop.service;

import com.example.eshop.dto.image.ImageResponse;
import com.example.eshop.entities.Image;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {


    String uploadFile(MultipartFile file);

    byte[] downloadFile(String fileName);

    void deleteFile(String fileName);

    ImageResponse getById(Long id);

    Image uploadFile(MultipartFile file, Image image);
}
