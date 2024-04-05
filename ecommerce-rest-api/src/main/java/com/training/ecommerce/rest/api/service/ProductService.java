package com.training.ecommerce.rest.api.service;

import com.training.ecommerce.rest.api.entity.Product;

import java.util.List;

public interface ProductService
{
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryId(Long catId);
    //List<Product> getProductsBySubId(Long subId);
     Product getProductById(Long catId, Long pId);

     Product creatProduct(Long catId,Product newProduct);
     Product uodateProduct(Long catId, Long pId, Product updatedProduct);

     void deleteProduct(Long catId, Long pId);

}
