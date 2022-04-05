package com.fges.category.service;

import com.fges.category.entity.Category;
import com.fges.category.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category){
        //log.info("Dans le saveCategory de CategoryService");
        return categoryRepository.save(category);
    }

    public Category findCategoryById(Long categoryId) {
        return   categoryRepository.findByCategoryId(categoryId);
    }
}
