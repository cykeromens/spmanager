/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.service.store.category.jpaImpl;

import com.cykeromens.model.store.category.Category;
import com.cykeromens.repository.store.category.CategoryRepository;
import com.cykeromens.service.store.category.CategoryService;
import java.util.Optional;
import javax.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cykeromens
 */
@Service("categoryService")
@Repository
@Transactional
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Object> findById(Long id) {
        return Optional.ofNullable(categoryRepository.findOne(id));
    }

    @Override @Transactional(readOnly = true)
    public Long countAll() {
        return categoryRepository.count();
    }

    @Override
    public void delete(Object entity) {
        Category category;
        if(entity instanceof Category){
            category = (Category) entity;
            categoryRepository.delete(category);
        }
    }

    @Override
    public Object save(Object entity) throws EntityExistsException {
        if(entity instanceof Category){
            return categoryRepository.save((Category)entity);
        }
        return null;
    }

}
