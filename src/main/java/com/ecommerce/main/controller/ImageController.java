package com.ecommerce.main.controller;
import com.ecommerce.main.dto.ImageDto;
import com.ecommerce.main.model.Image;
import com.ecommerce.main.reposnse.ApiResponse;
import com.ecommerce.main.service.image.imageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/image")
@RequiredArgsConstructor
public class ImageController {
    private final imageServiceImpl imageServiceImpl;

    @PostMapping("/upload/{productId}")
    public ResponseEntity<ApiResponse> uploadImages( @PathVariable("productId") Long productId, @RequestParam("files") List<MultipartFile> files){
        List<ImageDto> savedImages = imageServiceImpl.saveImage(files, productId);
        return ResponseEntity.ok(new ApiResponse("Images uploaded successfully", savedImages)); }

    @GetMapping("/getById/{productId}")
    public ResponseEntity<ApiResponse> getImagesByProductId(@PathVariable("productId") Long productId) {
        List<Image> images = imageServiceImpl.getImagesByProductId(productId);
        return ResponseEntity.ok(new ApiResponse("Fetched successfully", images));
    }

    @DeleteMapping("/DeleteById/{imagesId}")
    public ResponseEntity<ApiResponse> DeleteById(@PathVariable("imagesId") Long imagesId) {
        imageServiceImpl.deleteById(imagesId);
        return ResponseEntity.ok(new ApiResponse("deleted successfully", null));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("id") Long id) {
        Image image = imageServiceImpl.getImageById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .body(image.getImage());
}}
