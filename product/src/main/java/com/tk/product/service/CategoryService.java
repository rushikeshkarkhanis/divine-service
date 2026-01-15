package com.tk.product.service;

import com.tk.product.entity.Category;
import com.tk.product.repo.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category createCategory(Category Category) {
        return categoryRepo.save(Category);
    }

    public Category updateCategory(Long id, Category Category) {
        Category category = getCategoryById(id);
        category.setName(Category.getName());
        return categoryRepo.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
