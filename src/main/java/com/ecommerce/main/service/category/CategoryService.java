package com.ecommerce.main.service.category;
import com.ecommerce.main.dto.AddCategory;
import com.ecommerce.main.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> allCategory();
    void deleteById(Long id);
    Category findCategoryById(Long id);
    Category findByCategoryName(String categoryName);
    Category addCategory(AddCategory addCategory);
    Category updateCategory(Long id,AddCategory addCategory);

}
