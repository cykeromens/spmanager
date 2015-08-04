/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.service.store.product.jpaImpl;

import com.cykeromens.model.store.products.Product;
import com.cykeromens.repository.store.product.ProductRepository;
import com.cykeromens.service.store.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * @author cykeromens
 */
@Service("productService")
@Repository
@Transactional
public class ProductServiceImpl implements ProductService{

    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
       return productRepository.findAll(pageable);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Object> findById(Long id) {
        return Optional.ofNullable(productRepository.findOne(id));
    }

    @Override
    public Object save(Object object) {
        if(object instanceof Product){
            return productRepository.save((Product)object);
        }
        return null;
    }

    @Override
    public void delete(Object object) {
        if(object instanceof Product){
            productRepository.delete((Product)object);
        }
    }

    @Override @Transactional(readOnly = true)
    public Long countAll() {
        return productRepository.count();
    }
    

    @Override
    public Page<Product> productBySubCategory(Long subCatId, @PageableDefault(size = 20)Pageable pageable) {
        return productRepository.productOfSubCategory(subCatId,pageable);
    }

}
