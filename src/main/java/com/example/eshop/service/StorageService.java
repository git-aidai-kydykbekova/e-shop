package com.example.eshop.service;

import com.example.eshop.dto.image.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {


    String uploadFile(MultipartFile file);

    byte[] downloadFile(String fileName);

    void deleteFile(String fileName);

    ImageResponse getById(Long id);
}
