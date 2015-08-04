/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.service.location.jpaImpl;

import com.cykeromens.model.location.State;
import com.cykeromens.repository.location.StateRepository;
import com.cykeromens.service.location.StateService;
import java.util.Optional;

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
@Service("stateService")
@Repository
@Transactional
public class StateServiceImpl implements StateService{

    StateRepository stateRepository;

    public StateServiceImpl(){}
    @Autowired
    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override @Transactional(readOnly = true)
    public Page<State> findAll(Pageable pageable) {
        return stateRepository.findAll(pageable);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Object> findById(Long id) {
        return Optional.ofNullable(stateRepository.findOne(id));
    }
 
    @Override
    public Object save(Object object) {
        if(object instanceof State){
            return stateRepository.save((State)object);
        }else{
            return null;
        }
    }

    @Override
    public void delete(Object object) {
        if(object instanceof State){
           stateRepository.delete((State)object);
        }
    }

    @Override
    public Long countAll() {
        return stateRepository.count();
    }
    
}
