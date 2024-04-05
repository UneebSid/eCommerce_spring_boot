package com.training.ecommerce.rest.api.service;

import com.training.ecommerce.rest.api.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService
{
    List<Category> getAllCategories();
    Category getCategoryById(Long catId);

    Category createCategory(Category newCategory);

    Category updateCategory(Long catId,Category updateCategory);

    void deleteCategory(Long catId);

}
