package com.ecommerce.main.service.product;
import com.ecommerce.main.dto.ProductDto;
import com.ecommerce.main.exception.errors.ProductNotFound;
import com.ecommerce.main.model.Product;
import com.ecommerce.main.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class productServiceImpl implements productService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresentOrElse(item->productRepository.deleteById(id),()->{throw new ProductNotFound("Username not found");});

    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFound("Username not found"));
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

}
