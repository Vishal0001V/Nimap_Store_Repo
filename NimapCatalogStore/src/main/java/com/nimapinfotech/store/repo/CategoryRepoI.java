package com.nimapinfotech.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimapinfotech.store.model.Category;

@Repository
public interface CategoryRepoI extends JpaRepository<Category,Integer>{

}
