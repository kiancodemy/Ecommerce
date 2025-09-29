package com.ecommerce.main.cartItems.image;
import com.ecommerce.main.dto.ImageDto;
import com.ecommerce.main.exception.errors.FileStorageException;
import com.ecommerce.main.exception.errors.ProductNotFound;
import com.ecommerce.main.model.Image;
import com.ecommerce.main.model.Product;
import com.ecommerce.main.repository.ImageRepository;
import com.ecommerce.main.repository.ProductRepository;
import com.ecommerce.main.service.image.imageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor

public class imageServiceImpl implements imageService {

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    @Override
    public void deleteById(Long id) {
        imageRepository.deleteById(id);

    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(()->new ProductNotFound("Image not found"));
    }

    @Override
    public List<Image> getImagesByProductId(Long productId) {
        return imageRepository.findByProductId(productId);
    }

    @Override
    @Transactional
    public List<ImageDto> saveDImage(List<MultipartFile> files, Long productId) {
        Product findProduct = productRepository.findById(productId).orElseThrow(() -> new ProductNotFound("product not found"));
       return createNewImages(files, findProduct);}




    public List<ImageDto> createNewImages (List<MultipartFile> files,Product findProduct ){
        String base_Url = "/image/download/";
        List<ImageDto> imageDto =new ArrayList<>();
        for (MultipartFile file : files) {

                long sizeInMB = file.getSize() / (1024 * 1024);

                if (sizeInMB > 4) {
                    throw new FileStorageException("File too large : " + file.getOriginalFilename());
                }
                try {

                    Image img = new Image();
                    img.setName(file.getOriginalFilename());
                    img.setFileType(file.getContentType());
                    img.setImage(file.getBytes());
                    img.setProduct(findProduct);
                    Image saved = imageRepository.save(img);
                    String downloadUrl = base_Url + saved.getId();
                    saved.setDownloadedUrl(downloadUrl);
                    imageRepository.save(saved);
                    ImageDto newImage=new ImageDto(saved.getId(),saved.getName(),saved.getDownloadedUrl());
                    imageDto.add(newImage);

            } catch (IOException e ) {
                throw new FileStorageException("Failed to store file: " + file.getOriginalFilename());
            }}
        return imageDto;
    }

}
