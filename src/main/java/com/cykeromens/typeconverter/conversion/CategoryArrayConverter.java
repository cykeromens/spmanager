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

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author cykeromens
 */
public class CategoryArrayConverter implements Converter<String[], Collection<Category>>{


    CategoryService categoryService;

    @Autowired
    public CategoryArrayConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Collection convert(String[] catIdStr) {
        long catId;
        Collection<Category> catCollection= new ArrayList<Category>();
        
        try{
            for (String catStr : catIdStr) {
               catId = Integer.parseInt(catStr);
               Category category =(Category) categoryService.findById(catId).get();
               catCollection.add(category);
            }    
        } catch (NumberFormatException e) {

            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Category.class), catIdStr, null);
        }
        System.out.println(catCollection.toString());
        return catCollection;
 
    }

}
