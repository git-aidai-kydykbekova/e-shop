package com.example.eshop.controller;

import com.example.eshop.dto.image.ImageResponse;
import com.example.eshop.service.ProductService;
import com.example.eshop.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class ImageController {

    private StorageService storageService;
    private ProductService productService;
//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam(value = "file")MultipartFile file) {
//        return storageService.uploadFile(file);
//    }
    @PostMapping("/upload/{productId}")
    public String uploadFileById(@RequestHeader ("Authorization") String token, @RequestParam(value = "file")MultipartFile file,@RequestParam Long productId) {
        productService.uploadFile(token, file, productId);
        return "Image uploaded successfully!";
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = storageService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

//    @DeleteMapping("/delete/{filName}")
//    public String deleteFile(@PathVariable String fileName) {
//        storageService.deleteFile(fileName);
//        return "Image deleted successfully!";
//    }
    @DeleteMapping("/delete/{id}")
    public String deleteFile(@PathVariable Long id) {
        storageService.deleteFile(id);
        return "Image deleted successfully!";
    }
    @GetMapping("{id}")
    public ImageResponse getById(@PathVariable Long id) {
        return storageService.getById(id);
    }

}
