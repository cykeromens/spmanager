/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.service.location.jpaImpl;

import com.cykeromens.model.location.City;
import com.cykeromens.repository.location.CityRepository;
import com.cykeromens.service.location.CityService;
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

@Service("cityService")
@Repository
@Transactional
public class CityServiceImpl implements CityService{

    CityRepository cityRepository;

    public CityServiceImpl(){}
    
    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override @Transactional(readOnly = true)
    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Object> findById(Long id) {
        return Optional.ofNullable(cityRepository.findOne(id));
    }

    @Override
    public Object save(Object object) {
        if(object instanceof City){
            return cityRepository.save((City)object);
        }else{
            return null;
        }
    }

    @Override
    public void delete(Object object) {
        if(object instanceof City){
            cityRepository.delete((City)object);
        }
    }

    @Override
    public Long countAll() {
        return cityRepository.count();
    }
    
}
