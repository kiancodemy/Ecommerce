package com.ecommerce.main.service.product;
import com.ecommerce.main.dto.addProductDto;
import com.ecommerce.main.model.Product;
import java.util.List;

public interface productService {
    Product createProduct(addProductDto addProductDto);

    List<Product> getAllProducts();

    void deleteProduct(Long id);

    Product updateProduct(Long id, addProductDto addProductDto);

    List<Product> getProductsByCategoryId(Long id);

    Product getProductById(Long id);
}
