package com.ecommerce.main.controller;
import com.ecommerce.main.dto.ImageDto;
import com.ecommerce.main.model.Image;
import com.ecommerce.main.reposnse.ApiResponse;
import com.ecommerce.main.service.image.imageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/image")
@RequiredArgsConstructor
public class ImageController {
    private final imageServiceImpl imageServiceImpl;
    private final ModelMapper modelMapper;

    @PostMapping("/upload/{productId}")
    public ResponseEntity<ApiResponse> uploadImages( @PathVariable("productId") Long productId, @RequestParam("files") List<MultipartFile> files){
        List<ImageDto> savedImages = imageServiceImpl.saveDImage(files, productId);
        return ResponseEntity.ok(new ApiResponse("Images uploaded successfully", savedImages)); }

    @GetMapping("/getById/{productId}")
    public ResponseEntity<ApiResponse> getImagesByProductId(@PathVariable("productId") Long productId) {
        List<Image> images = imageServiceImpl.getImagesByProductId(productId);
        List<ImageDto> imageDto=images.stream().map(item->modelMapper.map(item,ImageDto.class)).toList();
        return ResponseEntity.ok(new ApiResponse("Fetched successfully", imageDto));
    }

    @DeleteMapping("/DeleteById/{imagesId}")
    public ResponseEntity<ApiResponse> DeleteById(@PathVariable("imagesId") Long imagesId) {
        imageServiceImpl.deleteById(imagesId);
        return ResponseEntity.ok(new ApiResponse("deleted successfully", null));
    }
    @GetMapping("/getImageById/{imageId}")
    public ResponseEntity<ApiResponse> getImageById(@PathVariable("imageId") Long ImageById) {
        Image images = imageServiceImpl.getImageById(ImageById);
        ImageDto imageDto=modelMapper.map(images,ImageDto.class);
        return ResponseEntity.ok(new ApiResponse("Fetched successfully", imageDto));
    }
}
