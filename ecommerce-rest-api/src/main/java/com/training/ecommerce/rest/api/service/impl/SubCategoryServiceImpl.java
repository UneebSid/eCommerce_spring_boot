package com.training.ecommerce.rest.api.service.impl;

import com.training.ecommerce.rest.api.entity.Category;
import com.training.ecommerce.rest.api.entity.SubCategory;
import com.training.ecommerce.rest.api.repository.CategoryRepository;
import com.training.ecommerce.rest.api.repository.SubCategoryRepository;
import com.training.ecommerce.rest.api.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubCategoryServiceImpl implements SubCategoryService
{
    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<SubCategory> getSubCategoriesByCatId(Long catId)
    {
        List<SubCategory> subCategories =subCategoryRepository.findByCategoryId(catId);
        return subCategories;
    }

    @Override
    public SubCategory getSubCategoryById(Long catId, Long subCatId)
    {
        SubCategory subCategory = subCategoryRepository.findById(subCatId)
                .orElseThrow(()-> new RuntimeException("Resource Not Found"));

        Category category = categoryRepository.findById(catId)
                .orElseThrow(()-> new RuntimeException("Resource Not Found"));
        if(!subCategory.getCategory().getId().equals(category.getId()))
        {
            throw new RuntimeException("Sub Category does not belong to the category");
        }
        return subCategory;
    }

    @Override
    public SubCategory createSubCategory(Long catId, SubCategory subCategory)
    {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(()->new RuntimeException("Resource Not Found"));
        category.getSubCategories().add(subCategory);
        subCategory.setCategory(category);
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory updateSubCategory(Long catId,Long subId ,SubCategory updateSubCategory)
    {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(()->new RuntimeException("Resource Not Found"));

        SubCategory subCategory = subCategoryRepository.findById(subId)
                .orElseThrow(()-> new RuntimeException("Resource Not Found"));
        if(!subCategory.getCategory().getId().equals(category.getId()))
        {
            throw new RuntimeException("Does not belong to the category");
        }

        subCategory.setSubCategoryName(updateSubCategory.getSubCategoryName());
        subCategory.setSubCategoryDescription(updateSubCategory.getSubCategoryDescription());
        subCategory.setPosition(updateSubCategory.getPosition());
        subCategory.setStatus(updateSubCategory.isStatus());
        subCategory.setSubCategoryImage(updateSubCategory.getSubCategoryImage());

        return subCategoryRepository.save(subCategory);
    }
   @Override
    public void deleteSubCategory(Long catId, Long subId)
    {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(()->new RuntimeException("Resource Not Found"));

        SubCategory subCategory = subCategoryRepository.findById(subId)
                .orElseThrow(()-> new RuntimeException("Resource Not Found"));
        if(!subCategory.getCategory().getId().equals(category.getId()))
        {
            throw new RuntimeException("Does not belong to the category");
        }

        subCategoryRepository.delete(subCategory);

    }

}
