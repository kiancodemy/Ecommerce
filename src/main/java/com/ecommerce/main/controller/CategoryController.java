package com.ecommerce.main.controller;
import com.ecommerce.main.dto.AddCategory;
import com.ecommerce.main.model.Category;
import com.ecommerce.main.reposnse.ApiResponse;
import com.ecommerce.main.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/allcategory")
    public ResponseEntity<ApiResponse> getAllCategories() {
        List<Category> all=categoryService.allCategory();
        return ResponseEntity.ok().body(new ApiResponse("success", all));
    }

    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<ApiResponse> deleteCategories(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse("success", null));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse> getCategoriesById(@PathVariable Long id) {
        Category category=categoryService.findCategoryById(id);
        return ResponseEntity.ok().body(new ApiResponse("success", category));
    }
    @PostMapping("/addCategory")
    public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody AddCategory addCategory) {
        Category category = categoryService.addCategory(addCategory);
        return ResponseEntity.ok().body(new ApiResponse("success", category));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> addCategory(@PathVariable Long id,@Valid @RequestBody AddCategory addCategory) {
        Category category = categoryService.updateCategory(id, addCategory);
        return ResponseEntity.ok().body(new ApiResponse("success", category));
    }

}
