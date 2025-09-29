package com.ecommerce.main.cartItems.category;
import com.ecommerce.main.dto.AddCategory;
import com.ecommerce.main.exception.errors.AlreadyExist;
import com.ecommerce.main.exception.errors.CategoryNotFound;
import com.ecommerce.main.model.Category;
import com.ecommerce.main.repository.CategoryRepository;
import com.ecommerce.main.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> allCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(item->categoryRepository.deleteById(item.getId()),()->{throw new CategoryNotFound("categoryNotFo8nd");});
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFound("categoryNotFo8nd"));
    }


    @Override
    public Category addCategory(AddCategory addCategory) {
        categoryRepository.findByName(addCategory.name()).ifPresent(c -> {
            throw new AlreadyExist("Category already exists");
        });
        Category category = new Category();
        category.setName(addCategory.name());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id,AddCategory addCategory) {
        Category category = findCategoryById(id);
        category.setName(addCategory.name());
        return categoryRepository.save(category);
    }

}
