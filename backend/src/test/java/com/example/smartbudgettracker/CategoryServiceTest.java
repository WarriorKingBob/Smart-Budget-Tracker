package com.example.smartbudgettracker;

import com.example.smartbudgettracker.Category.Category;
import com.example.smartbudgettracker.Category.CategoryService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    void testAddNewCategory() {
        Category category = categoryService.createCategory("Grocery");
        assertNotNull(category.getId());
    }

    @Test
    void testListAllCategories() {
        int before = categoryService.getAllCategories().size();
        categoryService.createCategory("Grocery");
        categoryService.createCategory("Electronics");
        List<Category> categories = categoryService.getAllCategories();
        assertEquals(before + 2, categories.size());
    }

    @Test
    void testUpdateCategory() {
        Category category = categoryService.createCategory("Grocery");
        categoryService.updateName(category.getId(), "Doctor");
        assertEquals("Doctor", category.getName());
    }

    @Test
    void testDeleteCategory() {
        Category category = categoryService.createCategory("Grocery");
        categoryService.deleteCategory(category.getId());
        Optional<Category> found = categoryService.getCategoryById(category.getId());
        assertFalse(found.isPresent());
    }
}
