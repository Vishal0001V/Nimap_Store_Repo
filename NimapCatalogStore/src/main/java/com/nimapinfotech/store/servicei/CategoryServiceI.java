package com.nimapinfotech.store.servicei;

import java.util.List;

import com.nimapinfotech.store.model.Category;

public interface CategoryServiceI {

	Category saveCategory(Category category);

	List<Category> getAllCategories();

	Category getCategoryById(int id);

	Category updateCategory(int id, Category category);

	void deleteCategory(int id);

}
