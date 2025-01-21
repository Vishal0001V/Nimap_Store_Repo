package com.nimapinfotech.store.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nimapinfotech.store.model.Category;
import com.nimapinfotech.store.model.Product;
import com.nimapinfotech.store.repo.CategoryRepoI;
import com.nimapinfotech.store.repo.ProductRepoI;
import com.nimapinfotech.store.servicei.ProductServiceI;

@Service
public class ProductServiceImpl implements ProductServiceI {

    @Autowired
    private ProductRepoI pri;

    @Autowired
    private CategoryRepoI cri;

    @Override
    public void saveProductForCategory(int categoryId, Product product) {
        Optional<Category> o = cri.findById(categoryId); // Fetching category by ID
        if (o.isPresent()) {
            Category category = o.get();

            if (category.getProducts() == null) {
                category.setProducts(new ArrayList<>()); // Initializing products list if null
            }
            
            category.getProducts().add(product); // Adding product to the category
            cri.save(category); // Saving updated category with the new product
        } else {
            throw new RuntimeException("Category ID not found"); // Handling case where category ID does not exist
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return pri.findAll(); // Fetching all products from the database
    }

    @Override
    public Product getProductById(int productId) {
        return pri.findById(productId)
                  .orElseThrow(() -> new RuntimeException("Product ID not found")); // Fetching product by ID or throwing exception if not found
    }

    @Override
    @Transactional
    public Product updateProduct(int productId, Product product) {
        Optional<Product> proOpt = pri.findById(productId); // Fetching product by ID
        if (proOpt.isPresent()) {
            Product existingProduct = proOpt.get();
            
            existingProduct.setName(product.getName()); // Updating product name
            existingProduct.setPrice(product.getPrice()); // Updating product price

            return pri.save(existingProduct); // Saving updated product
        } else {
            throw new RuntimeException("Product ID not found"); // Handling case where product ID does not exist
        }
    }

    @Override
    public void deleteProduct(int productId) {
        Optional<Product> productOpt = pri.findById(productId); // Fetching product by ID
        if (productOpt.isPresent()) {
            pri.deleteById(productId);  // Deleting product by ID
        } else {
            throw new RuntimeException("Product ID not found"); // Handling case where product ID does not exist
        }
    }

    @Override
    public List<Product> pagingAndSorting(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 2);  // Creating pageable with page number and size

        Page<Product> productPage = pri.findAll(pageable); // Fetching paginated products

        return productPage.getContent();  // Returning list of products on the requested page
    }
}
