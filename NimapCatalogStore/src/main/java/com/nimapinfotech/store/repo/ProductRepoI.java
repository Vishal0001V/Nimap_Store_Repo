package com.nimapinfotech.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimapinfotech.store.model.Product;

@Repository
public interface ProductRepoI extends JpaRepository<Product,Integer>{


}
