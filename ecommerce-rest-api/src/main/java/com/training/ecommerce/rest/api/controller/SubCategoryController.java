package com.training.ecommerce.rest.api.controller;

import com.training.ecommerce.rest.api.entity.SubCategory;
import com.training.ecommerce.rest.api.service.SubCategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Stack;

@RequestMapping("api/v1/subcategories")
@RestController
public class SubCategoryController
{
    @Autowired
    SubCategoryService subCategoryService;

    @GetMapping("/{catId}")
    public ResponseEntity<List<SubCategory>> getSubCategoriesByCatId(@PathVariable("catId") Long catId)
    {
        var data = subCategoryService.getSubCategoriesByCatId(catId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/{catId}/{subId}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable("catId") Long catId,@PathVariable("subId") Long subId)
    {
        var data = subCategoryService.getSubCategoryById(catId, subId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/{catId}")
    public ResponseEntity<SubCategory> createSubCategory(@PathVariable("catId") Long catId, @RequestBody SubCategory newSubCategory)
    {
        var data = subCategoryService.createSubCategory(catId,newSubCategory);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
    @PutMapping("/{catId}/{subId}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable("catId") Long catId,
                                                         @PathVariable("subId") Long subId,
                                                         @RequestBody SubCategory updateSubCategory)
    {
        var data = subCategoryService.updateSubCategory(catId,subId,updateSubCategory);
        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    @DeleteMapping("/{catId}/{subId}")
    public ResponseEntity<String> deleteSubCategory(Long catId, Long subId)
    {
        subCategoryService.deleteSubCategory(catId,subId);
        return  new ResponseEntity<>("Deleted.", HttpStatus.OK);
    }


}
