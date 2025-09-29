package com.ecommerce.main.controller;
import com.ecommerce.main.dto.AddCategory;
import com.ecommerce.main.dto.CategoryDto;
import com.ecommerce.main.model.Category;
import com.ecommerce.main.reposnse.ApiResponse;
import com.ecommerce.main.service.category.CategoryService;
import com.ecommerce.main.service.category.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/category")
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    private final ModelMapper modelMapper;

    @GetMapping("/allcategory")
    public ResponseEntity<ApiResponse> getAllCategories() {
        List<Category> all=categoryService.allCategory();
        List<CategoryDto> allDto = all.stream().map(item -> modelMapper.map(item, CategoryDto.class)).toList();
        return ResponseEntity.ok().body(new ApiResponse("success", allDto));
    }

    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<ApiResponse> deleteCategories(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse("success", null));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse> getCategoriesById(@PathVariable Long id) {
        Category category=categoryService.findCategoryById(id);
        CategoryDto categoryDto=modelMapper.map(category,CategoryDto.class);
        return ResponseEntity.ok().body(new ApiResponse("success", categoryDto));
    }
    @PostMapping("/addCategory")
    public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody AddCategory addCategory) {
        Category category = categoryService.addCategory(addCategory);
        CategoryDto categoryDto=modelMapper.map(category,CategoryDto.class);
        return ResponseEntity.ok().body(new ApiResponse("success",  categoryDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> addCategory(@PathVariable Long id,@Valid @RequestBody AddCategory addCategory) {
        Category category = categoryService.updateCategory(id, addCategory);
        CategoryDto categoryDto=modelMapper.map(category,CategoryDto.class);
        return ResponseEntity.ok().body(new ApiResponse("success", categoryDto));
    }

}
