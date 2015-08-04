/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.service.store.jpaImpl;

import com.cykeromens.model.store.Store;
import com.cykeromens.repository.store.StoreRepository;
import com.cykeromens.service.store.StoreService;
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
@Service("storeService")
@Repository
@Transactional
public class StoreServiceImpl implements StoreService{

    StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override @Transactional(readOnly = true)
    public Page<Store> findAll(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Object> findById(Long id) {
        return Optional.ofNullable(storeRepository.findOne(id));
    }

    @Override
    public Long countAll(){
        return storeRepository.count();
    }

    @Override
    public void delete(Object entity) {
        if(entity instanceof Store){
            storeRepository.delete((Store)entity);
        }
    }

    @Override
    public Object save(Object entity) {
        if(entity instanceof Store){
            return storeRepository.save((Store)entity);
        }else{
            return null;
        }
    }

    @Override @Transactional(readOnly = true)
    public Store findByStoreName(String storeName) {
        return storeRepository.findStore(storeName);
    }
}
