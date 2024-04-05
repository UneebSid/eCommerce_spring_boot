package com.training.ecommerce.rest.api.repository;

import com.training.ecommerce.rest.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
