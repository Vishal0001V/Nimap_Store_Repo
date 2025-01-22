package com.nimapinfotech.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimapinfotech.store.model.Category;
import com.nimapinfotech.store.servicei.CategoryServiceI;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceI csi;
// this is 
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return csi.saveCategory(category); // Saving a new category
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return csi.getAllCategories(); // Fetching all categories
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return csi.getCategoryById(id); // Fetching category by ID
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
        return csi.updateCategory(id, category); // Updating category details
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        csi.deleteCategory(id); // Deleting category by ID
    }
}
