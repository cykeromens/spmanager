/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.repository.store.product;

import com.cykeromens.model.store.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 *
 * @author cykeromens
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    
//    @Query("select p FROM Product p WHERE p.vendor.account.accountNumber=:accountNumber")
//    public Page<Product> productOfRetailers(@Param("accountNumber")Long accountNumber, Pageable pageable);
//    @Query("select p FROM Product p WHERE p.category=:category")
//    public Page<Product> productOfCategoryPage(@Param("category")String category,Pageable pageable);
//    @Query("select p FROM Product p WHERE p.subcategory=:subcategory")
//    public Page<Product> productOfSubCategoryPage(@Param("subcategory")String subcategory,Pageable pageable);
//    @Query("select p FROM Product p WHERE p.category.id=:catId")
//    public Page<Product> productOfCategory(@Param("catId")Long catId, Pageable pageable);
    @Query("select p FROM Product p WHERE p.subcategory.id=:subCatId")
    public Page<Product> productOfSubCategory(@Param("subCatId")Long subCatId, Pageable pageable);
    
}
