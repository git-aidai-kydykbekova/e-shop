package com.example.eshop.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.eshop.dto.image.ImageResponse;
import com.example.eshop.entities.Image;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.mapper.ImageMapper;
import com.example.eshop.repository.ImageRepository;
import com.example.eshop.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis()+"_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj));
        fileObj.delete();
        return "File uploaded " + fileName;
    }

    public byte[] downloadFile(String filename) {
        S3Object s3Object = s3Client.getObject(bucketName,filename);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFile(String filename) {
        s3Client.deleteObject(bucketName,filename);
        System.out.println("removed" + filename);
    }

    @Override
    public ImageResponse getById(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        if(image.isEmpty()){
            throw new NotFoundException("Image not found!", HttpStatus.NOT_FOUND);
        }

        return imageMapper.toDto(image.get());
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e ) {
            log.error("Error converting multipart file", e);
        }
        return convertedFile;
    }
}

