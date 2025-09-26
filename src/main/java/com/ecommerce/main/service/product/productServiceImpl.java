package com.ecommerce.main.service.product;
import com.ecommerce.main.dto.addProductDto;
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
    public Product createProduct(addProductDto addProductDto) {
        Product newProduct=new Product();
        newProduct.setBrand(addProductDto.brand());
        newProduct.setName(addProductDto.name());
        newProduct.setDescription(addProductDto.description());
        newProduct.setInventory(addProductDto.inventory());
        return productRepository.save(newProduct);

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
    public Product updateProduct(Long id, addProductDto addProductDto) {
        Product find=productRepository.findById(id).orElseThrow(()->new ProductNotFound("Username not found"));
        find.setName(addProductDto.name());
        find.setDescription(addProductDto.description());
        find.setInventory(addProductDto.inventory());
        find.setBrand(addProductDto.brand());
        return productRepository.save(find);
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
        return productRepository.findByName(name).orElseThrow(()->new ProductNotFound("Username not found"));
    }

}
