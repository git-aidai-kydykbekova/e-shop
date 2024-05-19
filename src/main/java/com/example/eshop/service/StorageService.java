package com.example.eshop.service;

import com.example.eshop.dto.image.ImageResponse;
import com.example.eshop.entities.Image;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {


    @Transactional
    Image uploadFile(MultipartFile file, Image oldDocument);

    Image uploadFile(MultipartFile file);

    void uploadFileToS3Bucket(MultipartFile file);

    byte[] downloadFile(String fileName);

    void deleteFile(Long id);

    ImageResponse getById(Long id);
}
