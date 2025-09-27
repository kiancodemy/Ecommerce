package com.ecommerce.main.controller;
import com.ecommerce.main.dto.addProductDto;
import com.ecommerce.main.model.Product;
import com.ecommerce.main.reposnse.ApiResponse;
import com.ecommerce.main.service.product.productServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/product")
@RequiredArgsConstructor
public class ProductController {
    private final productServiceImpl productServiceImpl;


    @PostMapping("/createProduct")
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody addProductDto addProductDt) {
        Product newProduct=productServiceImpl.createProduct(addProductDt);
        return ResponseEntity.ok().body(new ApiResponse("Product created successfully", newProduct));
    }

    @GetMapping("/allProducts")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> all=productServiceImpl.getAllProducts();
        return ResponseEntity.ok().body(new ApiResponse("fetched sucessfully",all));
    }


    @DeleteMapping("/deletedProduct/{id}")
   public ResponseEntity<ApiResponse> deleteProducts(@PathVariable("id") Long id){
        productServiceImpl.deleteProduct(id);
       return ResponseEntity.ok().body(new ApiResponse("deleted successfully", null));

   }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse> getBuID(@PathVariable("id") Long id) {
        Product product = productServiceImpl.getProductById(id);
        return ResponseEntity.ok().body(new ApiResponse("fetched sucessfully", product));
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ApiResponse> getByCategoryId(@PathVariable("id") Long id,  @Valid @RequestBody addProductDto addProductDto) {
        Product product = productServiceImpl.updateProduct(id, addProductDto);
        return ResponseEntity.ok().body(new ApiResponse("updated sucessfully", product));
    }


}
