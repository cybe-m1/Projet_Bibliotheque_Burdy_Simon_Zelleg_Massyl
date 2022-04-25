package com.fges.category.controller;

import javax.annotation.PostConstruct;

import com.fges.category.entity.Category;
import com.fges.category.service.CategoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/categories")
@Slf4j
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("/")
    public Category saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @GetMapping("/{id}")
    public Category findCategoryById(@PathVariable("id") Long categoryId){
        return categoryService.findCategoryById(categoryId);
    }
    
}
