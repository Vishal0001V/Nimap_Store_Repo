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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimapinfotech.store.model.Product;
import com.nimapinfotech.store.servicei.ProductServiceI;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceI psi;

    @PostMapping("/{categoryId}")
    public void createProduct(@PathVariable int categoryId, @RequestBody Product product) {
         psi.saveProductForCategory(categoryId, product); // Creating a product for a specific category
    }

    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        return psi.getAllProducts(); // Fetching all products
    }
    
    @GetMapping
    public List<Product> getAllProducts(@RequestParam int page) {
        List<Product> products = psi.pagingAndSorting(page); // Fetching paginated and sorted products
        return products;
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return psi.getProductById(productId); // Fetching product by ID
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable int productId, @RequestBody Product product) {
        return psi.updateProduct(productId, product); // Updating product details
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        psi.deleteProduct(productId); // Deleting product by ID
    }
}
