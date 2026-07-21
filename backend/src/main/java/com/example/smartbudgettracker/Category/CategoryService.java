package com.example.smartbudgettracker.Category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(UUID id) { return categoryRepository.findById(id); }

    public List<Category> getAllCategories() { return categoryRepository.findAll(); }

    public Category updateName(UUID id, String newName) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(newName);
        return categoryRepository.save(category);
    }

    public void deleteCategory(UUID id) { categoryRepository.deleteById(id); }
}
