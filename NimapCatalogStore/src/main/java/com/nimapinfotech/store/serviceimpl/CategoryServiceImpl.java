package com.nimapinfotech.store.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimapinfotech.store.model.Category;
import com.nimapinfotech.store.repo.CategoryRepoI;
import com.nimapinfotech.store.servicei.CategoryServiceI;

@Service
public class CategoryServiceImpl implements CategoryServiceI {

    @Autowired
    private CategoryRepoI cri;

    @Override
    public Category saveCategory(Category category) {
        return cri.save(category); // Saving category to the database
    }

    @Override
    public List<Category> getAllCategories() {
    	System.out.println(cri.findAll()); // Logging all categories to the console
        return cri.findAll(); // Fetching all categories from the database
    }

    @Override
    public Category getCategoryById(int id) {
        Optional<Category> c = cri.findById(id); // Fetching category by ID
        if (c.isPresent()) {
            return c.get(); // Returning the found category
        } else {
            throw new RuntimeException("Category Id not found "); // Handling case where ID does not exist
        }
    }


    @Override
    public Category updateCategory(int id, Category category) {
        Optional<Category> c = cri.findById(id); // Checking if category exists by ID
        if (c.isPresent()) {
            Category cat = c.get(); // Getting the existing category
            cat.setName(category.getName()); // Updating category name
            cat.setProducts(category.getProducts()); // Updating associated products
            return cri.save(cat); // Saving updated category
        } else {
            throw new RuntimeException("Category Id not found "); // Handling case where ID does not exist
        }
    }


    @Override
    public void deleteCategory(int id) {
        if (!cri.existsById(id)) { // Checking if category exists before deleting
            throw new RuntimeException("Category not found with id: " + id); // Handling case where ID does not exist
        }
        cri.deleteById(id); // Deleting the category by ID
    }
}
