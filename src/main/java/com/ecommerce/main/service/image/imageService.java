package com.ecommerce.main.service.image;

import com.ecommerce.main.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface imageService {
    void deleteById(Long id);

    Image getImageById(Long id);
    List<Image> getImagesByProductId(Long productId);
    Image saveImage(MultipartFile multipartFile, Long productId);
    Image updateImage(MultipartFile multipartFile, Long imageId);
    void deleteImageById(Long id);

}
