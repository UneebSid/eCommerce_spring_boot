package com.training.ecommerce.rest.api.repository;

import com.training.ecommerce.rest.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    List<Product> findByCategoryId(Long catId);
//    List<Product> findBySubId(Long subId);
}
