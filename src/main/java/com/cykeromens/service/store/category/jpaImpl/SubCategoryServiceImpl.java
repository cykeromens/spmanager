/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.service.store.category.jpaImpl;

import com.cykeromens.model.store.category.SubCategory;
import com.cykeromens.repository.store.category.SubCategoryRepository;
import com.cykeromens.service.store.category.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * @author cykeromens
 */
@Service("subCategoryService")
@Repository
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService{

    SubCategoryRepository subCategoryRepository;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override @Transactional(readOnly = true)
    public Page<SubCategory> findAll(Pageable pageable) {
        return subCategoryRepository.findAll(pageable);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Object> findById(Long id) {
        return Optional.ofNullable(subCategoryRepository.findOne(id));
    }

    @Override @Transactional(readOnly = true)
    public Long countAll() {
        return subCategoryRepository.count();
    }

    @Override
    public void delete(Object entity) {
        SubCategory subCategory;
        if(entity instanceof SubCategory){
            subCategory = (SubCategory) entity;
            subCategoryRepository.delete(subCategory);
        }
    }

    @Override
    public Object save(Object entity){
       if(entity instanceof SubCategory){
           return subCategoryRepository.save((SubCategory)entity);
       }
       return null;
    }
    
}
