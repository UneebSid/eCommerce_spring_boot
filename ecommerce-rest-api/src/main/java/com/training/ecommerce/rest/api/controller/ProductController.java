package com.training.ecommerce.rest.api.controller;

import com.training.ecommerce.rest.api.entity.Product;
import com.training.ecommerce.rest.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RequestMapping("/api/v1/category")
@RestController
public class ProductController
{
    @Autowired
    ProductService productService;

    @GetMapping("/allproducts")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        var data = productService.getAllProducts();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/{catId}/products")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable("catId") Long catId)
    {
        var data = productService.getProductsByCategoryId(catId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


    @GetMapping("/{catId}/products/{productId}")
    public  ResponseEntity<Product> getProductById(@PathVariable("catId") Long catId,@PathVariable("productId") Long pId)
    {
        var data = productService.getProductById(catId,pId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


    @PostMapping("/{catId}/products")
    public ResponseEntity<Product> createProduct(@PathVariable("catId") Long catId,@RequestBody Product newProduct)
    {
        var data = productService.creatProduct(catId,newProduct);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
    @PutMapping("/{catId}/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("catId") Long catId,@PathVariable("productId") Long pId,@RequestBody Product updateProduct)
    {
        var data = productService.uodateProduct(catId,pId,updateProduct);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{catId}/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("catId") Long catId,@PathVariable("productId") Long pId)
    {
        productService.deleteProduct(catId, pId);
        return new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }

}
