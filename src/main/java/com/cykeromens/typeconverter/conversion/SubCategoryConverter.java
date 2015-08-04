/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.typeconverter.conversion;

import com.cykeromens.model.store.category.SubCategory;
import com.cykeromens.service.store.category.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author cykeromens
 */
public class SubCategoryConverter implements Converter<String, SubCategory>{

    SubCategoryService subcategoryService;

    @Autowired
    public SubCategoryConverter(SubCategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @Override
    public SubCategory convert(String subCatIdStr) {
        long subcatId;
        SubCategory subcategory;
        try{
               subcatId = Integer.parseInt(subCatIdStr);
               subcategory =(SubCategory) subcategoryService.findById(subcatId).get();
        } catch (NumberFormatException e) {

            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(SubCategory.class), subCatIdStr, null);
        }
        return subcategory;
 
    }
}
