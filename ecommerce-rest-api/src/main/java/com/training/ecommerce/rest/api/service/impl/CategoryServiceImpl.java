package com.training.ecommerce.rest.api.service.impl;

import com.training.ecommerce.rest.api.entity.Category;
import com.training.ecommerce.rest.api.repository.CategoryRepository;
import com.training.ecommerce.rest.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {

        return  categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public Category createCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category updateCategory(Long catId, Category updateCategory) {
        Category category = categoryRepository
                .findById(catId).orElseThrow(()-> new RuntimeException("Not found"));
        category.setCategoryName(updateCategory.getCategoryName());
        category.setCategoryDescription(updateCategory.getCategoryDescription());
        category.setCategoryImage(updateCategory.getCategoryImage());
        category.setPosition(updateCategory.getPosition());
        category.setStatus(updateCategory.isStatus());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long catId)
    {
        Category category = categoryRepository.findById(catId).orElseThrow(()->new RuntimeException("Not found"));
        categoryRepository.delete(category);

    }
}
