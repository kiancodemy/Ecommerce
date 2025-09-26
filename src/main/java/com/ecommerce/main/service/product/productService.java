package com.ecommerce.main.service.product;
import com.ecommerce.main.dto.ProductDto;
import com.ecommerce.main.model.Product;
import java.util.List;
import java.util.Optional;

public interface productService {
    Product createProduct(ProductDto productDto);
    List<Product> getAllProducts();
    void deleteProduct(Long id);
    Product updateProduct(Long id, ProductDto productDto);
    List<Product> getProductsByCategoryId(Long id);
    Product getProductById(Long id);
    Product getProductByName(String name);}
