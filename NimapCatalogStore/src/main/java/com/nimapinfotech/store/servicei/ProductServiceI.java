package com.nimapinfotech.store.servicei;

import java.util.List;

import com.nimapinfotech.store.model.Product;

public interface ProductServiceI {

	void saveProductForCategory(int categoryId, Product product);

	List<Product> getAllProducts();

	Product getProductById(int productId);

	Product updateProduct(int productId, Product product);

	void deleteProduct(int productId);

	List<Product> pagingAndSorting(int page);

}
