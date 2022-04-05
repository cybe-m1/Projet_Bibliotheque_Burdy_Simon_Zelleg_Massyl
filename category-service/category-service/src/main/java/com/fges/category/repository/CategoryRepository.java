package com.fges.category.repository;



import com.fges.category.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category , Long> {

    Category findByCategoryId(Long categoryId);
    
}
