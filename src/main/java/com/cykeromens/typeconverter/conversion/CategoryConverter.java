/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.typeconverter.conversion;

import com.cykeromens.model.store.category.Category;
import com.cykeromens.service.store.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author cykeromens
 */
public class CategoryConverter implements Converter<String, Category>{

    CategoryService categoryService;

    @Autowired
    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category convert(String catIdStr) {
        long catId;
        Category category;
        try{
               catId = Integer.parseInt(catIdStr);
               category =(Category) categoryService.findById(catId).get();
        } catch (NumberFormatException e) {

            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Category.class), catIdStr, null);
        }
        return category;
 
    }

  
}

