package com.training.ecommerce.rest.api.repository;

import com.training.ecommerce.rest.api.entity.Product;
import com.training.ecommerce.rest.api.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>
{
    List<SubCategory> findByCategoryId(Long catId);
}
