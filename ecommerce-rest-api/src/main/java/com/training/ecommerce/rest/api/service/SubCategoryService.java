package com.training.ecommerce.rest.api.service;

import com.training.ecommerce.rest.api.entity.SubCategory;

import java.util.List;

public interface SubCategoryService
{
    List<SubCategory> getSubCategoriesByCatId(Long catId);

    SubCategory getSubCategoryById(Long catId, Long subCatId);

    SubCategory createSubCategory(Long catId, SubCategory subCategory);

    SubCategory updateSubCategory(Long catId, Long subId, SubCategory subCategory);

    void deleteSubCategory(Long catId,Long subId);
}
