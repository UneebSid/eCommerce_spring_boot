package com.training.ecommerce.rest.api.service.impl;

import com.training.ecommerce.rest.api.entity.Category;
import com.training.ecommerce.rest.api.entity.Product;
import com.training.ecommerce.rest.api.repository.CategoryRepository;
import com.training.ecommerce.rest.api.repository.ProductRepository;
import com.training.ecommerce.rest.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService
{

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Product> getProductsByCategoryId(Long catId) {
        List<Product> products= productRepository.findByCategoryId(catId);
        return products;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

//    @Override
//    public List<Product> getProductsBySubId(Long subId) {
//
//        return productRepository.findBySubId(subId);
//    }

    @Override
    public Product creatProduct(Long catId, Product newProduct)
    {
        Category category = categoryRepository.findById(catId).orElseThrow(()->new RuntimeException("Resource Not Found"));
        //category.getProducts().add(newProduct);
        newProduct.setCategory(category);
        return productRepository.save(newProduct);
    }

    @Override
    public Product uodateProduct(Long catId, Long pId, Product updatedProduct) {

        Product product = productRepository.findById(pId)
                .orElseThrow(()-> new RuntimeException("Resource Not Found!"));

        Category category = categoryRepository.findById(catId)
                .orElseThrow(()->new RuntimeException("Resource Not Found!"));

        if(!product.getCategory().getId().equals(category.getId()))
        {
            throw new RuntimeException("Product does not belong the category");
        }

        product.setProductName(updatedProduct.getProductName());
        product.setProductDescription(updatedProduct.getProductDescription());
        product.setProductImage(updatedProduct.getProductImage());
        product.setPosition(updatedProduct.getPosition());
        product.setStatus(updatedProduct.isStatus());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long catId, Long pId)
    {
        Product product = productRepository.findById(pId)
                .orElseThrow(()->new RuntimeException("Resource Not Found"));
        Category category = categoryRepository.findById(catId)
                .orElseThrow(()-> new RuntimeException("Resource Not Found"));
        if(!product.getCategory().getId().equals(category.getId()))
        {
            throw new RuntimeException("Product does not belong to category");
        }

        productRepository.delete(product);


    }

    @Override
    public Product getProductById(Long catId, Long pId) {
        Category category = categoryRepository.findById(catId).orElseThrow(()-> new RuntimeException("Not found"));

        Product product = productRepository.findById(pId).orElseThrow(()->new RuntimeException("Not found"));

        if(!product.getCategory().getId().equals(category.getId()))
        {
            throw new RuntimeException("Product does not belong the category");
        }

        return product;
    }
}
