package com.ecommerce.main.service.image;
import com.ecommerce.main.model.Image;
import com.ecommerce.main.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@Service
@RequiredArgsConstructor

public class imageServiceImpl implements imageService {
    private ImageRepository iimageRepository;

    private final ImageRepository imageRepository;
    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Image getImageById(Long id) {
        return null;
    }

    @Override
    public List<Image> getImagesByProductId(Long productId) {
        return imageRepository.findByProductId(productId);
    }

    @Override
    public Image saveImage(MultipartFile multipartFile, Long productId) {
        return null;
    }

    @Override
    public Image updateImage(MultipartFile multipartFile, Long imageId) {
        return null;
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.deleteById(id);



    }
}
